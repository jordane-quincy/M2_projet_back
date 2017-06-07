package org.istv.ske.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.exception.BadRequestException;
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
import com.google.gson.JsonParser;

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
		Offer offer = null;
		User user = null;
		String offerTitle = null;
		int duration = 0;
		String offerDescription = null;
		
		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			long idUser = content.get("idUser").getAsLong();
			offerTitle = content.get("titleOffer").getAsString();
			duration = content.get("duration").getAsInt();
			offerDescription = content.get("descriptionOffer").getAsString();
			user = userService.getUser(idUser);
			System.out.println(user.getUserFirstName());
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requete invalide : " + e.getMessage());
		}
		
		offer = offerService.createOffer(user, offerTitle, duration, offerDescription);
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
			String paramManquantMsg = "";
			if (content.get("idOffer") == null) {
				paramManquantMsg = paramManquantMsg + ": missing param 'idOffer' :";
			}
			if (!paramManquantMsg.equals("")) {
				throw new BadRequestException(paramManquantMsg);
			} else {
				throw e;
			}
		}
		offerService.deleteOffer(idOffer);
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST, headers = "Accept=application/json", produces = "Application/json")
	public List<Offer> lister(HttpServletRequest request) throws Exception {
		JsonObject content = null;
		int idUser = 0;
		try {
			content = jsonService.parse(request.getReader()).getAsJsonObject();
			idUser = content.get("idUser").getAsInt();
		} catch (Exception e) {
			String paramManquantMsg = "";
			if (content.get("idUser") == null) {
				paramManquantMsg = paramManquantMsg + ": missing param 'idUser' :";
			}
			if (!paramManquantMsg.equals("")) {
				throw new BadRequestException(paramManquantMsg);
			} else {
				throw e;
			}
		}

		// tester
		List<Offer> offers = offerService.getAll(idUser);
		return offers;
	}

}
