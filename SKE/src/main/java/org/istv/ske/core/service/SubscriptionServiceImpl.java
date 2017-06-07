package org.istv.ske.core.service;

import org.istv.ske.dal.Appointment;
import org.istv.ske.dal.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

	@Autowired
	AppointmentRepository app;
	
	
	@Override
	public boolean subscription(Appointment a) {
		app.save(a);
		return true;
	}

	@Override
	public boolean unsubscription(Appointment a) {
		app.delete(a);
		return true;
	}
	
	public Appointment findOne(Long id) {
		return app.findById(id);
	}

}
