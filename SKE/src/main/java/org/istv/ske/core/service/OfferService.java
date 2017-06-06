package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.Offer;
import org.istv.ske.dal.User;

public interface OfferService {
	public Offer createOffer(User idUser, String titleOffer, int duration, String descriptionOffer);
	public void deleteOffer(int idOffer);
	public Offer updateOffer(Offer offer);
	public List<Offer> getAll(int idUser);
}
