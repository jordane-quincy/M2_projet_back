package org.istv.ske.dal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Formation {
	
	@Id
	@GeneratedValue
	private int idFormation;
	private String formationLevel;
	private String formationName;

	
	
	

}
