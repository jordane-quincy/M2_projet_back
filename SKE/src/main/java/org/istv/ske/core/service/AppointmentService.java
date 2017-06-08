package org.istv.ske.core.service;

import java.sql.Date;
import java.util.List;

import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.AppointmentRepository;

public interface AppointmentService  {
	
	public Appointment createAppointment( Offer offer, User applicant, Date date);
	
	public void deleteAppointment( Appointment appointment);
	
	public Appointment updateAppointment( Appointment appointment,Offer offer, User applicant, Date date);
	
	public List<Appointment> getAll();
	public List<Appointment>findByApplicant( User applicant);
}
