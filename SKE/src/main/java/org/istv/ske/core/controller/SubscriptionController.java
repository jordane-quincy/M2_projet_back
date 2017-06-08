package org.istv.ske.core.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.service.OfferService;
import org.istv.ske.core.service.SubscriptionService;
import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.Appointment.AppointmentStatus;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.AppointmentRepository;
import org.istv.ske.dal.repository.UserRepository;
import org.istv.ske.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RequestMapping("/subscribe")
@RestController
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OfferService offerService;

	@Autowired
	private JsonService jsonService;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private TokenService tokenService;

	// S'inscrire à un cours
	@RequestMapping(value = { "/sub" }, method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody String subsciption(HttpServletRequest request) {

		JsonObject response = new JsonObject();
		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			final String idOffer = content.get("IdOffer").getAsString();
			Long idUser = tokenService.getUserIdByToken(request);
			User user = userRepository.findOne(idUser);
			Offer offer = offerService.findById(Long.valueOf(idOffer));
			if (offer == null)
				throw new BadRequestException("Cette offre n'existe pas.");
			if (user.getCredit() == 0) {
				response.addProperty("ok", false);
				throw new BadRequestException("Crédit insuffisant.");
			}
			Appointment app = new Appointment(offer, user, new Date(), AppointmentStatus.PENDING);

			if (subscriptionService.subscription(app)) {
				response.addProperty("ok", true);
			}
		} catch (Exception e) {
			response.addProperty("ok", false);
			response.addProperty("message", e.getMessage());
		}
		return jsonService.stringify(response);
	}

	// Se désinscrire à un cours
	@RequestMapping(value = { "/unsub" }, method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody String unsubsciption(HttpServletRequest request) {
		JsonObject response = new JsonObject();
		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			final String idOffer = content.get("IdOffer").getAsString();

			Appointment app = appointmentRepository.findOne(Long.valueOf(idOffer));
			if (app == null)
				throw new BadRequestException("Cette offre n'existe pas.");
			app.setStatus(AppointmentStatus.CANCELLED);
			if (subscriptionService.subscription(app))
				response.addProperty("ok", true);
		} catch (Exception e) {
			response.addProperty("ok", false);
			response.addProperty("message", e.getMessage());
		}
		return jsonService.stringify(response);
	}

	// MAJ d'une inscription, date/status
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody String update(HttpServletRequest request) {
		JsonObject response = new JsonObject();
		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			final String idOffer = content.get("IdOffer").getAsString();
			final String status = content.get("status").getAsString();
			final Long date = content.get("date").getAsLong();
			final int duration = content.get("duration").getAsInt();
			Appointment app = appointmentRepository.findOne(Long.valueOf(idOffer));
			if (app == null)
				throw new BadRequestException("Cette offre n'existe pas.");

			app.setStatus(AppointmentStatus.valueOf(status));
			app.setDate(new Date(date));

			if (status.equals("VALIDATED")) {
				User user = userRepository.findOne(app.getApplicant().getId());
				user.setCredit(user.getCredit() - duration);
				userRepository.save(user);
			}
			if (status.equals("FINISHED")) {
				User user = userRepository.findOne(app.getOffer().getUser().getId());
				user.setCredit(user.getCredit() + duration);
				userRepository.save(user);
				response.addProperty("credit", user.getCredit());
			}
			if (subscriptionService.subscription(app)) {
				response.addProperty("ok", true);

			}
		} catch (Exception e) {
			response.addProperty("ok", false);
			response.addProperty("message", e.getMessage());
		}
		return jsonService.stringify(response);
	}

	@RequestMapping(value = { "/subscriptions" }, method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Appointment> subscriptions(HttpServletRequest request) {
		List<Appointment> app = null;
		try {
			Long idUser = tokenService.getUserIdByToken(request);
			User user = userRepository.findOne(idUser);
			app = appointmentRepository.findByApplicant(user);
			if (app == null)
				throw new BadRequestException("Cette offre n'existe pas.");
			if (user == null)
				throw new BadRequestException("Cet utilisateur n'existe pas.");
		} catch (BadRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return app;
	}

	@RequestMapping(value = { "/participants" }, method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String participants(HttpServletRequest request) {
		List<Offer> offers = null;
		try {
			Long idUser = tokenService.getUserIdByToken(request);
			offers = offerService.findByUserId(idUser);
			if (offers == null)
				throw new BadRequestException("Cet utilisateur n'existe pas.");
		} catch (BadRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Appointment> apps = appointmentRepository.findByOfferOrderById(offers);

		try {
			if (apps == null)
				throw new BadRequestException("Cette offre n'existe pas.");
		} catch (BadRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonArray response = new JsonArray();

		for (Appointment app : apps) {
			JsonObject r = new JsonObject();
			r.addProperty("id", app.getId());
			r.addProperty("date", app.getDate().getTime());
			r.addProperty("offer", app.getOffer().getTitle());
			r.addProperty("duration", app.getOffer().getDuration());
			r.addProperty("status", app.getStatus().toString());
			r.addProperty("firstName", app.getApplicant().getUserFirstName());
			r.addProperty("lastName", app.getApplicant().getUserName());
			response.add(r);
		}
		return jsonService.stringify(response);
	}

	@RequestMapping(value = { "/participants" }, method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String curse(HttpServletRequest request) {
		List<Offer> offers = null;
		try {
			Long idUser = tokenService.getUserIdByToken(request);
			// offers = offerService.findByStatus("VALIDATED");
			if (offers == null)
				throw new BadRequestException("Cette offre n'existe pas.");
		} catch (BadRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Appointment> apps = appointmentRepository.findByOfferOrderById(offers);

		try {
			if (apps == null)
				throw new BadRequestException("Cette offre n'existe pas.");
		} catch (BadRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonArray response = new JsonArray();

		for (Appointment app : apps) {
			JsonObject r = new JsonObject();
			r.addProperty("id", app.getId());
			r.addProperty("date", app.getDate().getTime());
			r.addProperty("offer", app.getOffer().getTitle());
			r.addProperty("duration", app.getOffer().getDuration());
			r.addProperty("status", app.getStatus().toString());
			r.addProperty("firstName", app.getApplicant().getUserFirstName());
			r.addProperty("lastName", app.getApplicant().getUserName());
			response.add(r);
		}
		return jsonService.stringify(response);
	}

}
