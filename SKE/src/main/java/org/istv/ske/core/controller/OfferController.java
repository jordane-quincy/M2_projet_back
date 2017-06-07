package org.istv.ske.core.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.service.OfferService;
import org.istv.ske.core.service.UserService;
import org.istv.ske.dal.Offer;
import org.istv.ske.dal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

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
		// TODO add offer // erreur si user n'existe pas
		JsonObject content = null;
		Offer offer = null;
		try {
			// TODO tester + impl throw badException
			content = parser.parse(request.getReader()).getAsJsonObject();
			int idUser = content.get("idUser").getAsInt();
			String titleOffer = content.get("titleOffer").getAsString();
			int duration = content.get("duration").getAsInt();
			String descriptionOffer = content.get("descriptionOffer").getAsString();

			User user = userService.getUser(idUser);
			offer = offerService.createOffer(user, titleOffer, duration, descriptionOffer);
		} catch(Exception e ){
			String paramManquantMsg = "";
			if(content.get("idOffer") == null){
				paramManquantMsg = paramManquantMsg +": missing param 'idOffer' :";
			}
			if(!paramManquantMsg.equals("")){
				throw new BadRequestException(paramManquantMsg);
			}else{
				throw e;
			}
		} 
		return offer;
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST, headers = "Accept=application/json", produces = "Application/json")
	public void delete(HttpServletRequest request) throws Exception {
		int idOffer =0;
		JsonObject content = null;
		try {
			// TODO tester 
			content = parser.parse(request.getReader()).getAsJsonObject();
			idOffer = content.get("idOffer").getAsInt();
		} catch(Exception e ){
			String paramManquantMsg = "";
			if(content.get("idOffer") == null){
				paramManquantMsg = paramManquantMsg +": missing param 'idOffer' :";
			}
			if(!paramManquantMsg.equals("")){
				throw new BadRequestException(paramManquantMsg);
			}else{
				throw e;
			}
		} 
		offerService.deleteOffer(idOffer);
	}

	@RequestMapping(value = "/del/{iduser}", method = RequestMethod.POST, produces = "Application/json")
	public Offer update(Offer offer) {
		// TODO delete offer
		Offer offers = offerService.updateOffer(offer);
		return offers;
	}

	@RequestMapping(value = "/get/{iduser}", method = RequestMethod.POST, produces = "Application/json")
	public List<Offer> lister(int idUser) {
		// TODO lister offer
		List<Offer> offers = offerService.getAll(idUser);
		return offers;
	}

}
