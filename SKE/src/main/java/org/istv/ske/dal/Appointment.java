package org.istv.ske.dal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
@Data
@Entity
public class Appointment {
	
	@Id
	@GeneratedValue
	@ManyToOne
	@JoinColumn(name="appointment_id")
	private int appointmentID;
	private int applicantID;

	
}
