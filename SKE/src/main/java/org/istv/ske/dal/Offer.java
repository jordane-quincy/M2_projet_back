package org.istv.ske.dal;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Offer {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private int duration;
	
	private String title;
	
	private String description;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Subject subject;
	
	@OneToMany(mappedBy="offer")
	private Collection<Remark> remarks;
	
	@OneToMany(mappedBy="offer")
	private List<Appointment> appointments;
	
	public Offer() {
		
	}

	public Offer(int duration, String title, String description, User user, Subject subject, Collection<Remark> remarks) {
		this.duration = duration;
		this.title = title;
		this.description = description;
		this.user = user;
		this.subject = subject;
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

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Collection<Remark> getRemarks() {
		return remarks;
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