package org.istv.ske.dal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Scope("session")
public class Role {
	
	@Id
	@GeneratedValue
	private int id;
	private String type;
	
	@ManyToOne
	@JoinColumn(name="offre_id")
	//@JsonIgnore
	private Offre offre;
	private double montant;
	@JsonIgnore
	private double decouvert;
	
	public Role(){
		
	}

	public Role(Role client, double montant, String type, double decouvert) {
		super();
		this.client = client;
		this.montant = montant;
		this.type = type;
		this.decouvert = decouvert;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Role getClient() {
		return client;
	}

	public void setClient(Role client) {
		this.client = client;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

}
