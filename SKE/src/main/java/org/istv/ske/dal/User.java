package org.istv.ske.dal;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*@Entity
@Scope("session")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String userMail;
	private String userPassword;
	private String userName;
	private String userFirstName;
	private java.sql.Date birthday;
	
	//@OneToMany(mappedBy="user")
	//@JoinColumn(name="offre_id")
	//@JsonIgnore
	@OneToMany(mappedBy="user")
	private Collection<Offer> offers;
	
	public User(){
		
	}

	public User(User client, double montant, String type, double decouvert) {
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

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
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
*/