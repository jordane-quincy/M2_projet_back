package org.istv.ske.core.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.service.AppointmentService;
import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.service.OfferService;
import org.istv.ske.core.service.SubscriptionService;
import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.Appointment.AppointmentStatus;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.AppointmentRepository;
import org.istv.ske.dal.repository.UserRepository;
import org.istv.ske.messages.manager.NotificationManager;
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
	private AppointmentService appointmentService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private NotificationManager notificationManager;

	private static final DateFormat DF = new SimpleDateFormat("dd MMMM à HH:mm");

	// S'inscrire à un cours
	@RequestMapping(value = { "/sub" }, method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody String subsciption(HttpServletRequest request) {

		JsonObject response = new JsonObject();
		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			final String idOffer = content.get("IdOffer").getAsString();

			Long idUser = tokenService.getUserIdByToken(request);
			User user = userRepository.findOne(idUser);

			// On retrouve l'offre via l'id passé dans le json
			Offer offer = offerService.findById(Long.valueOf(idOffer));
			// Si aucune offre ne correspond, on lève une exception
			if (offer == null)
				throw new BadRequestException("Cette offre n'existe pas.");

			// On interndit l'inscritpion à son propre cours
			if (offer.getUser().getId() == user.getId()) {
				throw new BadRequestException("Vous ne pouvez pas vous inscire à vos cours.");
			}
			// On vérifie si le crédit est suffisant
			if (user.getCredit() < offer.getDuration()) {
				// response.addProperty("ok", false);
				// response.addProperty("message", "Crédit insuffisant");
				throw new BadRequestException("Crédit insuffisant");
			}
			// On met à jour le crédit du demandeur
			user.setCredit(user.getCredit() - offer.getDuration());
			userRepository.save(user);
			// On sauvegarde la demande avec le status pending, en attente
			Appointment app = new Appointment(offer, user, new Date(), AppointmentStatus.PENDING);

			// Notification d'une demande d'inscription à un cours
			notificationManager.createSimpleNotification(offer.getUser(), "Vous avez une demande d'inscription",
					user.getUserFirstName() + " " + user.getUserName() + " souhaite s'inscrire à votre cours \""
							+ offer.getTitle() + "\". Envoyez-lui une réponse !");

			if (subscriptionService.subscription(app)) {
				response.addProperty("ok", true);
				response.addProperty("user", user.getCredit());
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
			Long idUser = tokenService.getUserIdByToken(request);
			User user = userRepository.findOne(idUser);
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			final String idOffer = content.get("IdOffer").getAsString();

			Appointment app = appointmentRepository.findOne(Long.valueOf(idOffer));
			// Si nous n'avons aucune offre correspondant à l'id
			if (app == null)
				throw new BadRequestException("Cette offre n'existe pas.");
			// On vérifie si le status n'est pas déjà annulé et on crédite le
			// demandeur
			if (!app.getStatus().equals(AppointmentStatus.CANCELLED)) {
				app.setStatus(AppointmentStatus.CANCELLED);
				user.setCredit(user.getCredit() + app.getOffer().getDuration());
			}
			// On sauvegarde la modification et retourne le crédit mis à jour
			if (subscriptionService.subscription(app)) {
				response.addProperty("ok", true);
				response.addProperty("user", user.getCredit());
			}
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
			// On recherche l'utilisateur via le token
			Long idUser = tokenService.getUserIdByToken(request);
			User userAction = userRepository.findOne(idUser);

			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			final String idOffer = content.get("IdOffer").getAsString();
			AppointmentStatus status = null;
			final Long date = content.get("date").getAsLong();
			final int duration = content.get("duration").getAsInt();
			Appointment app = appointmentRepository.findOne(Long.valueOf(idOffer));
			try {
				status = AppointmentStatus.valueOf(content.get("status").getAsString());
			} catch (Exception e) {
				throw new BadRequestException("Status incorrrect");
			}
			if (app == null)
				throw new BadRequestException("Cette offre n'existe pas.");

			// Crédite à nouveau le demandeur en cas de refus
			if (status.equals(AppointmentStatus.REFUSED) && !app.getStatus().equals(AppointmentStatus.REFUSED)) {
				User user = userRepository.findOne(app.getApplicant().getId());
				user.setCredit(user.getCredit() + duration);
				userRepository.save(user);

				// Et on envoi une notification
				notificationManager.createSimpleNotification(app.getApplicant(), "Votre inscription a été refusée",
						app.getOffer().getUser().getUserFirstName() + " " + app.getOffer().getUser().getUserName()
								+ " a refusé votre demande d'inscription au cours \"" + app.getOffer().getTitle()
								+ "\"");
			}

			// Crédite le demandeur en cas d'annulation
			if (status.equals(AppointmentStatus.CANCELLED) && !app.getStatus().equals(AppointmentStatus.CANCELLED)) {
				User user = userRepository.findOne(app.getApplicant().getId());
				user.setCredit(user.getCredit() + duration);
				userRepository.save(user);

				// Notification en cas de demande annulée au demandeur
				notificationManager.createSimpleNotification(app.getApplicant(), "Votre inscription a été annulée",
						app.getOffer().getUser().getUserFirstName() + " " + app.getOffer().getUser().getUserName()
								+ " a annulé le rendez-vous du " + DF.format(date) + " pour le cours \""
								+ app.getOffer().getTitle() + "\"");

				// Notification en cas de demande annulée au propriétaire de
				// l'offre
				if (userAction.getId() == app.getApplicant().getId()) {
					notificationManager.createSimpleNotification(app.getOffer().getUser(), "La demande a été annulée",
							app.getApplicant().getUserFirstName() + " " + app.getApplicant().getUserName()
									+ " a annulé la demande de cours du " + DF.format(date) + " pour le cours \""
									+ app.getOffer().getTitle() + "\"");
				}

			}
			// Crédite le "prof" quand le cours est fini
			if (status.equals(AppointmentStatus.FINISHED) && !app.getStatus().equals(AppointmentStatus.FINISHED)) {
				User user = userRepository.findOne(app.getOffer().getUser().getId());
				user.setCredit(user.getCredit() + duration);
				userRepository.save(user);
				response.addProperty("credit", user.getCredit());
			}

			// Notification quand la demande a été validé
			if (status.equals(AppointmentStatus.VALIDATED) && !app.getStatus().equals(AppointmentStatus.VALIDATED)) {
				notificationManager.createSimpleNotification(app.getApplicant(), "Votre inscription a été validée",
						"Votre cours \"" + app.getOffer().getTitle() + "\" aura lieu le " + DF.format(date) + " avec "
								+ app.getOffer().getUser().getUserFirstName() + " "
								+ app.getOffer().getUser().getUserName());
			}

			app.setStatus(status);
			app.setDate(new Date(date));
			if (subscriptionService.subscription(app)) {
				response.addProperty("ok", true);
				response.addProperty("user", app.getOffer().getUser().getCredit());
			}
		} catch (Exception e) {
			response.addProperty("ok", false);
			response.addProperty("message", e.getMessage());
		}
		return jsonService.stringify(response);
	}

	// Return les cours auquels l'utilisateur connecté est inscrit et confirmé
	@RequestMapping(value = { "/subscriptions" }, method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Appointment> subscriptions(HttpServletRequest request) {
		List<Appointment> app = null;
		try {
			Long idUser = tokenService.getUserIdByToken(request);
			User user = userRepository.findOne(idUser);
			app = appointmentRepository.findByApplicantAndStatus(user, AppointmentStatus.VALIDATED);
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

	// Récupère les cours à suivre en fonction du status
	@RequestMapping(value = {
			"/attemptSubscriptions" }, method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody List<Appointment> attemptSubscriptions(HttpServletRequest request) {
		List<Appointment> app = null;
		try {
			Long idUser = tokenService.getUserIdByToken(request);
			User user = userRepository.findOne(idUser);
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			final String status = content.get("status").getAsString();
			app = appointmentRepository.findByApplicantAndStatus(user, AppointmentStatus.valueOf(status));
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

	// Récupère les cours à donner en fonction du status
	@RequestMapping(value = { "/participants" }, method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody String participants(HttpServletRequest request) throws Exception {
		Long idUser = tokenService.getUserIdByToken(request);
		JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
		final String status = content.get("status").getAsString();
		List<Appointment> apps = appointmentService.findByOwnerId(idUser, AppointmentStatus.valueOf(status));

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

	// // Cours validé que je vais donner
	// @RequestMapping(value = { "/courses" }, method = RequestMethod.GET,
	// headers = "Accept=application/json")
	// public @ResponseBody String curse(HttpServletRequest request) {
	// List<Appointment> appointments = null;
	// List<Offer> offers = null;
	// try {
	// Long idUser = tokenService.getUserIdByToken(request);
	// offers = offerService.findByUserId(idUser);
	// appointments =
	// appointmentRepository.findByStatusAndOffer(AppointmentStatus.VALIDATED,
	// offers);
	//
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// JsonArray response = new JsonArray();
	// for (Appointment app : appointments) {
	// JsonObject r = new JsonObject();
	// r.addProperty("id", app.getId());
	// r.addProperty("date", app.getDate().getTime());
	// r.addProperty("offer", app.getOffer().getTitle());
	// r.addProperty("duration", app.getOffer().getDuration());
	// r.addProperty("status", app.getStatus().toString());
	// r.addProperty("firstName", app.getApplicant().getUserFirstName());
	// r.addProperty("lastName", app.getApplicant().getUserName());
	// response.add(r);
	// }
	// return jsonService.stringify(response);
	// }

}
