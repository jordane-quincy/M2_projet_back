package org.istv.ske.dal.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * La table Remark contient tous les comentaires pour une offre.
 * 
 * @param id
 *            Identifiant
 * @param text
 *            Contenu du commentaire.
 * @param grade
 *            Note donnée par le participant à l'offre apres que le cours soit
 *            passé.
 * @param Offer
 *            Offre à laquelle est lié le commentaire.
 */
@Entity
public class Remark {

	@Id
	@GeneratedValue
	private long id;

	private String text;

	@Min(0)
	@Max(5)
	private int grade;

	@ManyToOne
	@JsonIgnore
	private Offer offer;

	public Remark() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constucteur utilisé dans la partie core pour créér un commentaire pour
	 * une offre donnée.
	 * 
	 * @param text
	 * @param offer
	 * @param grade
	 */
	public Remark(String text, Offer offer, int grade) {
		this.text = text;
		this.offer = offer;
		this.grade = grade;
	}

	public long getId() {
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
