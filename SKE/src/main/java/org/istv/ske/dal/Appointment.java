package org.istv.ske.dal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "appointment")
public class Appointment {
	

	@Id
	@GeneratedValue
	@JoinColumn(name="appointment_id")
	private int appointmentID;
	@JoinColumn(name="user_id")
	private int applicantID;
	
	public Appointment(){
		
	}

	public int getAppointmentID() {
		return appointmentID;
	}
	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}
	public int getApplicantID() {
		return applicantID;
	}
	public void setApplicantID(int applicantID) {
		this.applicantID = applicantID;
	}
	
	@Override
	public String toString() {
		return "Appointment [appointmentID=" + appointmentID + ", applicantID=" + applicantID + "]";
	}
	public Appointment(int appointmentID, int applicantID) {
		super();
		this.appointmentID = appointmentID;
		this.applicantID = applicantID;
	}
}
