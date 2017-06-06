package org.istv.ske.dal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "skill")
public class Skill {
	
	@Id
	@GeneratedValue
	@ManyToOne
	@JoinColumn(name="skill_id")
	private int skillID;
	private String description;
	public int getSkillID() {
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
	@Override
	public String toString() {
		return "Skill [skillID=" + skillID + ", description=" + description + "]";
	}
	public Skill(int skillID, String description) {
		super();
		this.skillID = skillID;
		this.description = description;
	}
	public Skill() {
		super();
	}
	
	
	


}