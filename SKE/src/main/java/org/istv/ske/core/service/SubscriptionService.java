package org.istv.ske.core.service;

import org.springframework.stereotype.Service;

@Service
public interface SubscriptionService {

	public boolean subscription(int idOffer, int idUser);
	public boolean unsubscription(int idOffer, int idUser);
}
