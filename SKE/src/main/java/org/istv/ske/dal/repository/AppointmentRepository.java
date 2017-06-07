package org.istv.ske.dal.repository;

import org.istv.ske.dal.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer>{
	public Appointment findById(Long id);
}
