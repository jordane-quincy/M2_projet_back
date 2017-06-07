package org.istv.ske.dal.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Skill {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String description;

	@ManyToOne
	private User user;
	
	@ManyToMany
	private List<User> validators;
	
	public Skill() {
		// TODO Auto-generated constructor stub
	}

	public Skill(String description, User user) {
		super();
		this.description = description;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getValidators() {
		return validators;
	}

	public void setValidators(List<User> validators) {
		this.validators = validators;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	
	
}