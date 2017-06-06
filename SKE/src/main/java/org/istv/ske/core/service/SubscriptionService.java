package org.istv.ske.core.service;

public interface SubscriptionService {

	public boolean subscription(int idOffer, int idUser);
	public boolean unsubscription(int idOffer, int idUser);
}
