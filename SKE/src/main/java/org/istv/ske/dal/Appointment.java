package org.istv.ske.dal;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Appointment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Offer offer;
	
	@ManyToOne
	private User applicant;
	
	private Date date;
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;
	
	public enum AppointmentStatus {
		PENDING,
		VALIDATED,
		REFUSED,
		FINISHED,
		CANCELLED
	}
	
	public Appointment() {
		
	}

	public Appointment(Offer offer, User applicant, Date date, AppointmentStatus status) {
		this.offer = offer;
		this.applicant = applicant;
		this.date = date;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

}
