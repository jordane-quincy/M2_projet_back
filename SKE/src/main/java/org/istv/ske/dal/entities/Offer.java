package org.istv.ske.dal.entities;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Offer {

	@Id
	@GeneratedValue
	private Long id;

	private int duration;

	private String title;

	private String description;

	@ManyToOne
	@JsonIgnore
	private User user;

	@ManyToOne
	private Domain domain;

	@OneToMany(mappedBy = "offer")
	private Collection<Remark> remarks;

	@OneToMany(mappedBy = "offer")
	@JsonIgnore
	private List<Appointment> appointments;

	public Offer() {

	}

	public Offer(int duration, String title, String description, User user, Domain domain, Collection<Remark> remarks) {
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

	public Collection<Remark> getRemarks() {
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

	public void setRemarks(Collection<Remark> remarks) {
		this.remarks = remarks;
	}

}