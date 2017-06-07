package org.istv.ske.dal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {

	@Id
	@GeneratedValue
	@JoinColumn(name = "offer_id")
	private int appointmentID;
	private int applicantID;
<<<<<<< Updated upstream

	private org.joda.time.DateTime appointmentDateTime;
	private String appointmentStatus;

	public Appointment() {

=======
	
	private org.joda.time.DateTime AppointmentDateTime;
	private String AppointmentStatus;
	
	
	public org.joda.time.DateTime getAppointmentDateTime() {
		return AppointmentDateTime;
	}

	public void setAppointmentDateTime(org.joda.time.DateTime appointmentDateTime) {
		AppointmentDateTime = appointmentDateTime;
	}

	public String getAppointmentStatus() {
		return AppointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		AppointmentStatus = appointmentStatus;
	}

	public Appointment(){
		
>>>>>>> Stashed changes
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
