package org.istv.ske.core.service;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.istv.ske.dal.Appointment;
import org.istv.ske.dal.Offer;
import org.istv.ske.dal.User;
import org.istv.ske.dal.repository.AppointmentRepository;

public class AppointmentServiceImpl implements AppointmentService{
AppointmentRepository appointmentRepository ;
	@Override
	public Appointment createAppointment(Offer offer, User applicant, Date date) {
	Appointment appointment = new Appointment();
	appointment.setOffer(offer);
	appointment.setApplicant(applicant);
	appointment.setDate(date);
	appointmentRepository.save(appointment);
		return appointment;
	}

	@Override
	public void deleteAppointment(Appointment appointment) {
		 appointmentRepository.delete(appointment);
	}

	@Override
	public Appointment updateAppointment(Appointment appointment, Offer offer, User applicant, Date date) {
		Appointment appointmentFound =  appointmentRepository.findOne( appointment.getId().intValue());
		 
		  appointmentFound.setApplicant(applicant);
		  appointmentFound.setOffer(offer);
		  appointmentFound.setDate(date);
		  appointmentRepository.save(appointmentFound);
		return appointmentFound;
	}

	@Override
	public List<Appointment> getAll() {
		
		return  (List<Appointment>) appointmentRepository.findAll();
	
	}

}
