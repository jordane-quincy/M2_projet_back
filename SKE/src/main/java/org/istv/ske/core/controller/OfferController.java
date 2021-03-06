package org.istv.ske.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.configuration.ApplicationConfig;
import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.exception.InternalException;
import org.istv.ske.core.service.DomainService;
import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.service.OfferService;
import org.istv.ske.core.service.UserService;
import org.istv.ske.core.utils.FieldReader;
import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.Appointment.AppointmentStatus;
import org.istv.ske.dal.entities.Domain;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.AppointmentRepository;
import org.istv.ske.dal.repository.UserRepository;
import org.istv.ske.messages.manager.NotificationManager;
import org.istv.ske.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

@RestController
@RequestMapping("/offer")
public class OfferController {

	@Autowired
	private OfferService offerService;

	@Autowired
	private UserService userService;

	@Autowired
	private JsonService jsonService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private DomainService domainService;

	@Autowired
	private NotificationManager notificationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	/**
	 * creation d'une offre, elle apartiendra a l'utilisateur courant
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public Offer create(HttpServletRequest request) throws Exception {
		// control input
		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();

		Long userId = tokenService.getUserIdByToken(request);

		String title = FieldReader.readString(object, "title");
		String description = FieldReader.readString(object, "description");
		Long duration = FieldReader.readLong(object, "duration");
		Long domainId = FieldReader.readLong(object, "domainId");
		// verification que l offre cree a bien un domaine pour l'acceuilir
		Domain domain = domainService.findById(domainId);
		if (domain == null)
			throw new BadRequestException("Ce domaine n'existe pas");

		User user = userService.getUser(userId);
		try {
			Offer created = offerService.createOffer(user, title, duration.intValue(), description, domain);
			return created;
		} catch (Exception e) {
			throw new InternalException("Erreur lors de la création de l'offre : " + e.getMessage());
		}
	}

	/**
	 * suppresion d'une offre qui nous appartient
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json", produces = "application/json")
	public String delete(HttpServletRequest request, @PathVariable(required = true) Long id) throws Exception {
		Long userId = tokenService.getUserIdByToken(request);
		Offer offer = offerService.findById(id);
		// controle
		if (offer == null)
			throw new BadRequestException("Cette offre n'existe pas");

		if (offer.getUser().getId() != userId)
			throw new BadRequestException("Vous ne pouvez pas supprimer une offre qui ne vous appartient pas");

		try {
			offerService.updateStatus(id, true);
			for (Appointment app : offer.getAppointments()) {
				if (app.getStatus() != AppointmentStatus.CANCELLED && app.getStatus() != AppointmentStatus.FINISHED
						&& app.getStatus() != AppointmentStatus.REFUSED) {
					notificationManager.createSimpleNotification(app.getApplicant(), "Offre supprimée", "L'offre \""
							+ app.getOffer().getTitle() + "\" à laquelle vous étiez inscrit(e) a été supprimée.");
					app.setStatus(AppointmentStatus.CANCELLED);
					appointmentRepository.save(app);
					User user = userService.getUser(app.getApplicant().getId());
					user.setCredit(user.getCredit() + app.getOffer().getDuration());
					userRepository.save(user);
				}
			}
			return ApplicationConfig.JSON_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalException("Erreur lors de la suppression de l'offre : " + e.getMessage());
		}
	}

	/**
	 * lister les offres de utilisateur connecté
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json", produces = "Application/json")
	public List<Offer> lister(HttpServletRequest request) throws Exception {
		Long userId = tokenService.getUserIdByToken(request);
		try {
			return offerService.findByUserId(userId);
		} catch (Exception e) {
			throw new InternalException("Impossible de récupérer les offres de cet utilisateur : " + e.getMessage());
		}
	}

	/**
	 * mise a jour d'une offre a l'ID données si elle existe et nous appartient
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST, headers = "Accept=application/json", produces = "Application/json")
	public Offer update(HttpServletRequest request, @PathVariable(required = true) Long id) throws Exception {
		// control input
		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();

		Long userId = tokenService.getUserIdByToken(request);

		String title = FieldReader.readString(object, "title");
		String description = FieldReader.readString(object, "description");
		Long duration = FieldReader.readLong(object, "duration");
		Long domainId = FieldReader.readLong(object, "domainId");

		Domain domain = domainService.findById(domainId);
		if (domain == null)
			throw new BadRequestException("Ce domaine n'existe pas");

		Offer offer = offerService.findById(id);
		if (offer == null)
			throw new BadRequestException("Cette offre n'existe pas");

		if (offer.getUser().getId() != userId)
			throw new BadRequestException("Vous ne pouvez pas modifier une offre qui ne vous appartient pas");

		try {
			Offer updated = offerService.updateOffer(id, title, duration.intValue(), description, domain);
			return updated;
		} catch (Exception e) {
			throw new InternalException("Erreur lors de la modification de l'offre : " + e.getMessage());
		}
	}

	/**
	 * ajouter un commentaire a une offre 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/comment/{id}", method = RequestMethod.POST, headers = "Accept=application/json", produces = "Application/json")
	public Offer addCommentary(HttpServletRequest request, @PathVariable(required = true) Long id) throws Exception {
		// control input
		Long userId = tokenService.getUserIdByToken(request);
		User user = userService.getUser(userId);
		// vérifier que le mec qui commente a bien suivi ce cours

		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();

		String comment = FieldReader.readString(object, "comment");
		Long grade = FieldReader.readLong(object, "grade");

		Offer offer = offerService.findById(id);
		if (offer == null)
			throw new BadRequestException("Cette offre n'existe pas");

		try {
			offerService.addComment(id, comment, grade.intValue());
			notificationManager.createSimpleNotification(offer.getUser(), "Vous avez reçu un avis",
					user.getUserFirstName() + " " + user.getUserName() + " vous a laissé un avis pour le cours \""
							+ offer.getTitle() + "\".");
			return offer;
		} catch (Exception e) {
			throw new InternalException("Impossible d'ajouter un commentaire à l'offre : " + e.getMessage());
		}
	}

	/**
	 * lister toutes les offres
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
	public List<Offer> findAllOffer() throws Exception {
		try {
			List<Offer> offer = offerService.findAllAvailable();
			return offer;
		} catch (Exception e) {
			throw new RuntimeException("Impossible de récupérer la liste des offres");
		}
	}

	/**
	 * rechercher les offres selont un filtre de caracteristiques
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/filter", method = RequestMethod.POST, produces = "application/json")
	public List<Offer> filter(HttpServletRequest request) throws Exception {

		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();

		String keywords = FieldReader.readString(object, "keywords");
		List<Long> domains = FieldReader.readLongArray(object, "domains");
		JsonObject duration = FieldReader.readObject(object, "duration");
		Long durationMin = FieldReader.readLong(duration, "lower");
		Long durationMax = FieldReader.readLong(duration, "upper");
		Boolean teacher = FieldReader.readBoolean(object, "teacher");
		Boolean student = FieldReader.readBoolean(object, "student");
		Double minimalAvgGrade = FieldReader.readDouble(object, "minavggrade");

		try {
			List<Offer> offer = offerService.search(keywords, domains, durationMin.intValue(), durationMax.intValue(),
					teacher, student, minimalAvgGrade.intValue());
			return offer;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Impossible de récupérer la liste des offres");
		}
	}

}
