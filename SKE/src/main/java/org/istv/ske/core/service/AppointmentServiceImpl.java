package org.istv.ske.core.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentRepository appointmentRepository ;

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
		Appointment appointmentFound =  appointmentRepository.findOne(appointment.getId());
		appointmentFound.setApplicant(applicant);
		appointmentFound.setOffer(offer);
		appointmentFound.setDate(date);
		appointmentRepository.save(appointmentFound);
		return appointmentFound;
	}

	@Override
	public List<Appointment> getAll() {
		List<Appointment> appointments = new ArrayList<>();
		for(Appointment a : appointmentRepository.findAll()) {
			appointments.add(a);
		}
		return appointments;
	}

}