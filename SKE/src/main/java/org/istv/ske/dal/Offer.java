package org.istv.ske.dal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "offer")
public class Offer {
	
	@Id
	@GeneratedValue
	private int OfferId;
	
	private int duration;
	private String titleOffer;
	private String descriptionOffer;
	
	@ManyToOne
	private User user;
	@ManyToOne
	private Subject subject;
	
	public Offer(){
		
	}

	public int getId() {
		return OfferId;
	}

	public void setId(int id) {
		this.OfferId = id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getTitleOffer() {
		return titleOffer;
	}

	public void setTitleOffer(String titleOffer) {
		this.titleOffer = titleOffer;
	}

	public String getDescriptionOffer() {
		return descriptionOffer;
	}

	public void setDescriptionOffer(String descriptionOffer) {
		this.descriptionOffer = descriptionOffer;
	}



}