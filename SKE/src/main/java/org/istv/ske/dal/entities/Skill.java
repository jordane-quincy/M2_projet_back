package org.istv.ske.dal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * La table Skill permet de définir les compétences d'un utilisateur.
 * 
 * @param id
 *            Identifiant.
 * @param label
 *            Nom de la compétence (ex: Java, JS, C#, ...)
 */
@Entity
public class Skill {

	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true)
	private String label;

	public Skill() {
		// TODO Auto-generated constructor stub
	}

	public Skill(String label) {
		this.label = label;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}