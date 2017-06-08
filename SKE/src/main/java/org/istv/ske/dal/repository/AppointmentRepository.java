package org.istv.ske.dal.repository;

import java.util.List;

import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

	public List<Appointment> findByApplicant(User user);

	public List<Appointment> findByOfferOrderById(List<Offer> offers);

	public List<Appointment> findByStatus(String status);
}
