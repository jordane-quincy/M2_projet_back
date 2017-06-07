package org.istv.ske.dal.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Remark {

	@Id
	@GeneratedValue
	private int id;

	private String text;
	
	private int grade;

	@ManyToOne
	@JsonIgnore
	private Offer offer;

	public Remark() {
		// TODO Auto-generated constructor stub
	}

	public Remark(String text, Offer offer, int grade) {
		this.text = text;
		this.offer = offer;
		this.grade = grade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

}
