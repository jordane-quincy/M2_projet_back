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
@Table(name = "offer")
public class Offer {
	
	@Id
	@GeneratedValue
	private int id;
	
	private int duration;
	private String titleOffer;
	private String descriptionOffer;
	
	@ManyToOne
	private User user;
	@ManyToOne
	private Subject subject;
	
	public Offer(){
		
	}

	public Offer(int id, int duration, String titleOffer, String descriptionOffer, User userId, Subject subjectId) {
		super();
		this.id = id;
		this.type = type;
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

	public Offre getClient() {
		return client;
	}

	public void setClient(Offre client) {
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
