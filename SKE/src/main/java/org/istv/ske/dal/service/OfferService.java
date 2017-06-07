package org.istv.ske.dal.service;

import java.util.List;

import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;

public interface OfferService {
	public Offer createOffer(User user, String titleOffer, int duration, String descriptionOffer,Long subjectID);
	public void deleteOffer(long idOffer);
	public Offer updateOffer(Offer offer);
	public List<Offer> getAll(long idUser);
	public Offer getOffer(long Offer);
}
