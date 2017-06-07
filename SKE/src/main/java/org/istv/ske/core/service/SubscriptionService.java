package org.istv.ske.core.service;

import org.istv.ske.dal.entities.Appointment;

public interface SubscriptionService {

	public boolean subscription(Appointment a);
	
	public boolean unsubscription(Appointment a);
	
}
