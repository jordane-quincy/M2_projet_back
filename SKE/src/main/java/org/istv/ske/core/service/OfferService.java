package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.entities.Domain;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;

public interface OfferService {
	
	public Offer createOffer(User user, String title, int duration, String description, Domain domain);
	
	public void deleteOffer(Long id);
	
	public List<Offer> findByUserId(Long userId);
	
	public Offer findById(Long offerId);
	
	public Offer updateOffer(Long offerId, String title, int duration, String description, Domain domain);
	
	public Offer addComment(Long offerId, String comment, int grade);

	public List<Offer> findAll();
}
