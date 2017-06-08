package org.istv.ske.core.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.service.OfferService;
import org.istv.ske.core.service.SubscriptionService;
import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.Appointment.AppointmentStatus;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.AppointmentRepository;
import org.istv.ske.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

	// S'inscrire à un cours
	@RequestMapping(value = { "/sub" }, method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody String subsciption(HttpServletRequest request) {

		// String s = "{\"IdOffer\": 1,\"Token\": \"untoken\"}";
		JsonObject response = new JsonObject();
		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			final String idOffer = content.get("IdOffer").getAsString();
			// TODO find user by token
			User user = userRepository.findOne(1L);
			Offer offer = offerService.findById(Long.valueOf(idOffer));
			if (user.getCredit() == 0) {
				response.addProperty("ok", false);
				return jsonService.stringify(response);
			}
			Appointment app = new Appointment(offer, user, new Date(), AppointmentStatus.PENDING);

			if (subscriptionService.subscription(app)) {
				response.addProperty("ok", true);
				user.setCredit(user.getCredit() - 1);
				userRepository.save(user);
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
			System.out.println(idOffer);
			
			Appointment app = appointmentRepository.findOne(Long.valueOf(idOffer));
			app.setStatus(AppointmentStatus.valueOf(status));
			app.setDate(new Date(date));

			if (subscriptionService.subscription(app))
				response.addProperty("ok", true);
		} catch (Exception e) {
			response.addProperty("ok", false);
			response.addProperty("message", e.getMessage());
		}
		return jsonService.stringify(response);
	}

	@RequestMapping(value = { "/subscriptions/{id}" }, method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Appointment> subscriptions(HttpServletRequest request,
			@PathVariable(required = true) Long id) {
		User user = userRepository.findOne(id);
		List<Appointment> app = appointmentRepository.findByApplicant(user);
		System.out.println(app.get(0).getOffer().getDescription());
		return app;
	}
	
	@RequestMapping(value = { "/participants" }, method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Appointment> participants(HttpServletRequest request,
			@PathVariable(required = true) Long id) {
		User user = userRepository.findOne(id);
		List<Appointment> app = appointmentRepository.findByApplicant(user);
		System.out.println(app.get(0).getOffer().getDescription());
		return app;
	}

}
