package org.istv.ske.core.service;

import org.istv.ske.dal.Appointment;
import org.springframework.stereotype.Service;


public interface SubscriptionService {

	public boolean subscription(Appointment a);
	public boolean unsubscription(Appointment a);
	public Appointment findOne(Long id);
}
