package org.istv.ske.dal;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "offer")
public class Offer {
	
	@Id
	@GeneratedValue
	private int id;
	
	private int duration;
	
	private String title;
	
	private String description;
	
	@ManyToOne
	private User user;
	@ManyToOne
	private Subject subject;
	
	@OneToMany(mappedBy="offer")
	private Collection<View> views;
	
	public Offer() {
		
	}

	public Offer(int duration, String title, String description, User user, Subject subject, Collection<View> views) {
		super();
		this.duration = duration;
		this.title = title;
		this.description = description;
		this.user = user;
		this.subject = subject;
		this.views = views;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Collection<View> getViews() {
		return views;
	}

	public void setViews(Collection<View> views) {
		this.views = views;
	}

}