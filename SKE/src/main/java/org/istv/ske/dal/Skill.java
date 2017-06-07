package org.istv.ske.dal;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "skill")
public class Skill {
	
	@Id
	@GeneratedValue
	@JoinColumn(name="skill_id")
	private long skillID;
	private String description;
	@ManyToMany
	  @JoinTable(
	      name="Master",
	      joinColumns=@JoinColumn(name="skillID", referencedColumnName="skillID"),
	      inverseJoinColumns=@JoinColumn(name="userID", referencedColumnName="id"))
	  private List<User> users;
	public long getSkillID() {
		return skillID;
	}
	public void setSkillID(int skillID) {
		this.skillID = skillID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public Skill( String description, List<User> users) {
		super();
		this.description = description;
		this.users = users;
	}
	public Skill() {
		super();
	}
	
	
	


}