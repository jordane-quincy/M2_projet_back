package org.istv.ske.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.service.OfferService;
import org.istv.ske.core.service.UserService;
import org.istv.ske.dal.Offer;
import org.istv.ske.dal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@RequestMapping("/my_offer")
public class OfferController {

	@Autowired
	OfferService offerService;

	@Autowired
	UserService userService;

	private JsonParser parser = new JsonParser();

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json", produces = "Application/json")
	public Offer create(HttpServletRequest request) throws Exception {
		JsonObject content = null;
		Offer offer = null;
		long idUser;
		String titleOffer, descriptionOffer;
		int duration;
		try {
			// TODO tester
			content = parser.parse(request.getReader()).getAsJsonObject();
			idUser = content.get("idUser").getAsLong();
			titleOffer = content.get("titleOffer").getAsString();
			duration = content.get("duration").getAsInt();
			descriptionOffer = content.get("descriptionOffer").getAsString();

		} catch (Exception e) {
			String paramManquantMsg = "";
			if (content.get("idUser") == null) {
				paramManquantMsg = paramManquantMsg + ": missing param 'idUser' :";
			}
			if (content.get("titleOffer") == null) {
				paramManquantMsg = paramManquantMsg + ": missing param 'titleOffer' :";
			}
			if (content.get("duration") == null) {
				paramManquantMsg = paramManquantMsg + ": missing param 'duration' :";
			}
			if (content.get("descriptionOffer") == null) {
				paramManquantMsg = paramManquantMsg + ": missing param 'descriptionOffer' :";
			}
			if (!paramManquantMsg.equals("")) {
				throw new BadRequestException(paramManquantMsg);
			} else {
				throw e;
			}
		}
		offer = offerService.createOffer(idUser, titleOffer, duration, descriptionOffer,0);
		return offer;
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST, headers = "Accept=application/json", produces = "Application/json")
	public void delete(HttpServletRequest request) throws Exception {
		long idOffer = 0;
		JsonObject content = null;
		try {
			// TODO tester
			content = parser.parse(request.getReader()).getAsJsonObject();
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

	@RequestMapping(value = "/del/{iduser}", method = RequestMethod.POST, produces = "Application/json")
	public Offer update(Offer offer) {
		// TODO delete offer rereflechir
		Offer offers = offerService.updateOffer(offer);
		return offers;
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST, headers = "Accept=application/json", produces = "Application/json")
	public List<Offer> lister(HttpServletRequest request) throws Exception {
		JsonObject content = null;
		int idUser = 0;
		try {
			content = parser.parse(request.getReader()).getAsJsonObject();
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
