package org.istv.ske.dal.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * La table Appointment permet de lister les rendez-vous d'échanges de
 * connaissances entre deux utilisateurs.
 * 
 * @param id
 *            L'identifiant du rendez-vous.
 * @param offer
 *            Un rendez-vous est lier à une offer pour connaitre l'offre à
 *            laquelle adhere l'utilisateur demandeur.
 * @param applicant
 *            L'utilisateur demandeur.
 * @param date
 *            La date du rendez-vous.
 * @param status
 *            Status actuel du rendez-vous choisi parmi une énumération (en
 *            attente, validé, refusé, terminé, annulé)
 */
@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Offer offer;

	@ManyToOne
	@JsonIgnore
	private User applicant;

	private Date date;

	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;

	public enum AppointmentStatus {
		PENDING, VALIDATED, REFUSED, FINISHED, CANCELLED
	}

	public Appointment() {

	}

	/**
	 * Constucteur utilisé dans la partie core pour créér un rendez-vous
	 * 
	 * @param offer
	 * @param applicant
	 * @param date
	 * @param status
	 */
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
