package org.istv.ske.dal.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author M2
 */
@Entity
public class Skill {
	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true)
	private String label;
	@Filters({ @Filter(name = "betweenLength", condition = "0 <= length and length <= 5") })
	private int grade;

	@ManyToOne
	@JsonIgnore
	private User user;

	@ManyToMany
	@JsonIgnore
	private List<User> validators;

	public Skill() {
		// TODO Auto-generated constructor stub
	}

	public Skill(String description, User user) {
		super();
		this.label = description;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return label;
	}

	public void setDescription(String description) {
		this.label = description;
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

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}