package org.istv.ske.dal;

import java.util.Date;

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
	@JoinColumn(name="offer_id")
	private Long appointmentId;
	
	private int applicantId;
	
	private Date date;
	
	private String status;
	
	public Appointment() {
		
	}

	public Appointment(int applicantId, Date date, String status) {
		this.applicantId = applicantId;
		this.date = date;
		this.status = status;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
