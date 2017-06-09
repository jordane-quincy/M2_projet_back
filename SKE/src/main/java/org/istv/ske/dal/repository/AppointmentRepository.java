package org.istv.ske.dal.repository;

import java.util.List;

import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.Appointment.AppointmentStatus;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

	List<Appointment> findByApplicant(User user);

	List<Appointment> findByOffer(List<Offer> offers);

	List<Appointment> findByStatusAndOffer(AppointmentStatus validated, List<Offer> offers);

	List<Appointment> findByApplicantAndStatus(User user, AppointmentStatus status);

}
