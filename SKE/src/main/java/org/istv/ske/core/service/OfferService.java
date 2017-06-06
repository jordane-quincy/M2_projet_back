package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.Offer;
import org.istv.ske.dal.User;

public interface OfferService {
	public Offer createOffer(User idUser, String titleOffer, int duration, String descriptionOffer);
	public void deleteOffer(String email);
	public User updateOffer(Offer offer);
	public List<Offer> getAll();
}
