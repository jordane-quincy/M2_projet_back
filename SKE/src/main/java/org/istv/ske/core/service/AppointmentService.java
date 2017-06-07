package org.istv.ske.core.service;

import java.sql.Date;
import java.util.List;

import org.istv.ske.dal.Appointment;
import org.istv.ske.dal.Offer;
import org.istv.ske.dal.User;

public interface AppointmentService {
	public Appointment createAppointment( Offer offer, User applicant, Date date);
	public void deleteAppointment( Appointment appointment);
	public Appointment updateAppointment( Appointment appointment,Offer offer, User applicant, Date date);
	public List<Appointment> getAll();
}
