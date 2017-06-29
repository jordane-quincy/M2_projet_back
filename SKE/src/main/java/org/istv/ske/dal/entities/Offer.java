package org.istv.ske.dal.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * La table offre liste toutes les offres disponibles dans l'application. Une
 * offre étant un cours proposé par un utilisateur en fonction de ses
 * compétences dans un domaine.
 * 
 * @param id
 *            Identifiant de l'offre.
 * @param duration
 *            Durée du cours.
 * @param title
 *            Titre du cours.
 * @param description
 *            Description du cours.
 * @param keywords
 *            Mots clés pour pouvoir accéder à cette offre dans la barre de
 *            recherche.
 * @param status
 *            Indique si l'offre est active. Permet aussi de différencier les
 *            offres actives ou non dans l'affichage.
 * @param user
 *            L'utilisateur fournisseur.
 * @param domain
 *            Une offre est toujours liée à un domaine.
 * @param remarks
 *            Liste des avis/commentaires pour une offre, ces commentaires ne
 *            peuvent être ajoutés qu'une fois le cours terminé.
 * @param appointments
 *            Les rendez-vous pour une offre. Obligatoirement bien sur à des
 *            horaires différents.
 */
@Entity
public class Offer {

	@Id
	@GeneratedValue
	private Long id;

	private int duration;

	private String title;

	private String description;

	@JsonIgnore
	private String keywords;

	private boolean status;

	@ManyToOne
	private User user;

	@ManyToOne
	private Domain domain;

	@OneToMany(mappedBy = "offer")
	private List<Remark> remarks = new ArrayList<>();

	@OneToMany(mappedBy = "offer")
	@JsonIgnore
	private List<Appointment> appointments = new ArrayList<>();

	public Offer() {

	}

	/**
	 * Constucteur utilisé dans la partie core pour créér une offre de cours.
	 * 
	 * @param duration
	 * @param title
	 * @param description
	 * @param user
	 * @param domain
	 * @param remarks
	 */
	public Offer(int duration, String title, String description, User user, Domain domain, List<Remark> remarks) {
		this.duration = duration;
		this.title = title;
		this.description = description;
		this.user = user;
		this.domain = domain;
		this.remarks = remarks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Remark> getRemarks() {
		return remarks;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public void setRemarks(List<Remark> remarks) {
		this.remarks = remarks;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}