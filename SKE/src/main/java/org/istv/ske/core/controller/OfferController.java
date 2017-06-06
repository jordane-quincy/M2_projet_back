package org.istv.ske.core.controller;

import java.util.ArrayList;
import java.util.List;

import org.istv.ske.core.service.OfferService;
import org.istv.ske.core.service.UserService;
import org.istv.ske.dal.Offer;
import org.istv.ske.dal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my_offer")
public class OfferController {

	@Autowired
	OfferService offerService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "Application/json")
	public Offer create(int idUser, String titleOffer, int duration, String descriptionOffer) {
		// TODO add offer // erreur si user n'existe pas 
		User user =  userService.getUser(idUser);
		Offer offer = offerService.createOffer(user, titleOffer, duration, descriptionOffer);
		return offer;
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "Application/json")
	public void delete(int idOffer) {
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
