package org.istv.ske.dal;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "formation")
public class Formation {
	
	
	@Id
	@GeneratedValue
	private int idFormation;
	private String formationLevel;
	private String formationName;
	
	@OneToMany(mappedBy="formation")
	private Collection<User> users;
	
	
	public int getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}
	public String getFormationLevel() {
		return formationLevel;
	}
	public void setFormationLevel(String formationLevel) {
		this.formationLevel = formationLevel;
	}
	public String getFormationName() {
		return formationName;
	}
	public void setFormationName(String formationName) {
		this.formationName = formationName;
	}
	@Override
	public String toString() {
		return "Formation [idFormation=" + idFormation + ", formationLevel=" + formationLevel + ", formationName="
				+ formationName + "]";
	}
	public Formation(int idFormation, String formationLevel, String formationName) {
		super();
		this.idFormation = idFormation;
		this.formationLevel = formationLevel;
		this.formationName = formationName;
	}
	public Formation() {
		super();
	}
	
	

}
