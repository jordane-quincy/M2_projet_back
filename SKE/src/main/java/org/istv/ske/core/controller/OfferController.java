package org.istv.ske.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.exception.InternalException;
import org.istv.ske.core.service.JsonService;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.service.OfferService;
import org.istv.ske.dal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public Offer create(HttpServletRequest request) throws Exception {
		User user = null;
		String offerTitle = null, offerDescription = null;
		int duration = 0;
		long subectID = 0L;

		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			long idUser = content.get("idUser").getAsLong();
			offerTitle = content.get("titleOffer").getAsString();
			duration = content.get("duration").getAsInt();
			offerDescription = content.get("descriptionOffer").getAsString();
			user = userService.getUser(idUser);
			subectID = content.get("subectID").getAsLong();
			System.out.println(user.getUserFirstName());
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requete invalide : " + e.getMessage());
		}
		Offer offer = null;
		try {
			offer = offerService.createOffer(user, offerTitle, duration, offerDescription, subectID);
		} catch (Exception e) {
			throw new InternalException("Erreur lors de la création de l'utilisateur");
		}
		return offer;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public void delete(HttpServletRequest request) throws Exception {
		long idOffer = 0;
		JsonObject content = null;
		try {
			// TODO tester
			content = jsonService.parse(request.getReader()).getAsJsonObject();
			idOffer = content.get("idOffer").getAsLong();
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requete invalide : " + e.getMessage());
		}
		try {
			offerService.deleteOffer(idOffer);
		} catch (Exception e) {
			throw new InternalException("Erreur lors de la création de l'utilisateur");
		}
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST, headers = "Accept=application/json", produces = "Application/json")
	public List<Offer> lister(HttpServletRequest request) throws Exception {
		JsonObject content = null;
		int idUser = 0;
		try {
			content = jsonService.parse(request.getReader()).getAsJsonObject();
			idUser = content.get("idUser").getAsInt();
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requete invalide : " + e.getMessage());
		}
		List<Offer> offers = null;
		try {
			offers = offerService.getAll(idUser);
		} catch (Exception e) {
			throw new InternalException("Erreur lors de la création de l'utilisateur");
		}
		return offers;
	}

}
