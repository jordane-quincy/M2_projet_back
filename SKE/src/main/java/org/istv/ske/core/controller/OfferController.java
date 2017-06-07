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

	@RequestMapping(value = "/create/{userId}", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public Offer create(HttpServletRequest request,//
			@PathVariable(required=true) Long userId) throws Exception {
		User user = null;
		String offerTitle = null, offerDescription = null;
		int duration = 0;
		long subectID = 0L;

		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			offerTitle = content.get("titleOffer").getAsString();
			duration = content.get("duration").getAsInt();
			offerDescription = content.get("descriptionOffer").getAsString();
			user = userService.getUser(userId);
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
	public List<Offer> lister(HttpServletRequest request,//
			@PathVariable(required=true) Long userId) throws Exception {
		List<Offer> offers = null;
		try {
			offers = offerService.getAll(userId);
		} catch (Exception e) {
			throw new InternalException("Erreur lors de la création de l'utilisateur");
		}
		return offers;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, headers = "Accept=application/json", produces = "Application/json")
	public Offer update(HttpServletRequest request) throws Exception{
		Offer offers = null;
		
		String offerTitle = null, offerDescription = null;
		int duration = 0;
		long offerID = 0L ,subectID = 0L;

		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			offerID = content.get("offerID").getAsLong();
			offerTitle = content.get("titleOffer").getAsString();
			duration = content.get("duration").getAsInt();
			offerDescription = content.get("descriptionOffer").getAsString();
			subectID = content.get("subectID").getAsLong();
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requete invalide : " + e.getMessage());
		}
		
		offers = offerService.updateOffer(offerID,offerTitle,duration,offerDescription,subectID);
		return offers;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, headers = "Accept=application/json", produces = "Application/json")
	public Offer addCommentary(HttpServletRequest request) throws Exception{
		Offer offers = null;
		long offerID = 0L;
		String comment;
		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			offerID = content.get("offerID").getAsLong();
			comment = content.get("comment").getAsString();
			
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requete invalide : " + e.getMessage());
		}
		offers = offerService.addCommentary(offerID,comment);
		return offers;
	}
}
