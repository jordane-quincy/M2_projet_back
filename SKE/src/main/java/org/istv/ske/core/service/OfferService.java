package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.entities.Domain;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;

public interface OfferService {

	Offer createOffer(User user, String title, int duration, String description, Domain domain);

	List<Offer> findByUserId(Long userId);

	Offer findById(Long offerId);

	Offer updateOffer(Long offerId, String title, int duration, String description, Domain domain);

	void updateStatus(Long offerId, boolean status);

	Offer addComment(Long offerId, String comment, int grade);

	List<Offer> findAll();
	
	List<Offer> findAllAvailable();

	List<Offer> search(String keywords, List<Long> domains, int durationMin, int durationMax, boolean teacher,
			boolean student, int minAvgGrade);
}
