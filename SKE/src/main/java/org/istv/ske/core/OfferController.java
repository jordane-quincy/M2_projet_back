package org.istv.ske.core;

import java.util.ArrayList;
import java.util.List;

import org.istv.ske.dal.Offer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my_offer")
public class OfferController {

	@RequestMapping(value = { "/get/{iduser}" }, method = RequestMethod.GET, headers = "Accept=*/*", produces = "Application/json")
	@ResponseBody
	public List<Offer> getOffers(@PathVariable("iduser") String idUser){
		//TODO lister offer 
		List<Offer> offers = new ArrayList<Offer>();
		return offers;
	}
	
	@RequestMapping(value = { "/add/{iduser}" }, method = RequestMethod.GET, headers = "Accept=*/*", produces = "Application/json")
	@ResponseBody
	public Offer addOffers(@PathVariable("iduser") String idUser){
		//TODO add offer 
		Offer offers = new Offer();
		return offers;
	}
	
	@RequestMapping(value = { "/del/{iduser}" }, method = RequestMethod.GET, headers = "Accept=*/*", produces = "Application/json")
	@ResponseBody
	public Offer deleteOffers(@PathVariable("iduser") String idUser){
		//TODO delete offer 
		Offer offers = new Offer();
		return offers;
	}
}
