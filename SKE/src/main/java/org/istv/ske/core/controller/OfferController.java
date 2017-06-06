package org.istv.ske.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToOne;

import org.istv.ske.dal.Offer;
import org.istv.ske.dal.Subject;
import org.istv.ske.dal.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my_offer")
public class OfferController {

	@RequestMapping(value = { "/get/{iduser}" }, method = RequestMethod.POST, headers = "Accept=*/*", produces = "Application/json")
	public List<Offer> getOffers(@PathVariable("iduser") String idUser){
		//TODO lister offer 
		List<Offer> offers = new ArrayList<Offer>();
		return offers;
	}
	
	@RequestMapping(value = { "/add/{iduser}/{title}/{duration}" }, method = RequestMethod.POST, headers = "Accept=*/*", produces = "Application/json")
	public Offer addOffers(@PathVariable("iduser") String idUser,//
			@PathVariable("title") String titleOffer,
			@PathVariable("duration") int duration){
		//TODO add offer 

		
		Offer offers = new Offer();
		offers.setTitleOffer(titleOffer);
		offers.setDuration(duration);
	//	offers.setDescriptionOffer(descriptionOffer);
		return offers;
	}
	
	@RequestMapping(value = { "/del/{iduser}" }, method = RequestMethod.POST, headers = "Accept=*/*", produces = "Application/json")
	public Offer deleteOffers(@PathVariable("iduser") String idUser){
		//TODO delete offer 
		Offer offers = new Offer();
		return offers;
	}
}
