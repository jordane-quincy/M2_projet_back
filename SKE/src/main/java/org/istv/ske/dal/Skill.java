package org.istv.ske.dal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity
@Scope("session")
public class Skill {
	
	@Id
	@GeneratedValue
	@ManyToOne
	@JoinColumn(name="skill_id")
	private int skillID;
	private String description;
	
	
	


}