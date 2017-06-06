package org.istv.ske.dal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "view")
public class View {
	
	@Id
	@GeneratedValue
	private int idView;
	private String textView;
	
	@ManyToOne
	private Offer offer;
	
	public Offer getOffer() {
		return offer;
	}



	public void setOffer(Offer offer) {
		this.offer = offer;
	}



	public int getIdView() {
		return idView;
	}



	public void setIdView(int idView) {
		this.idView = idView;
	}



	public String getTextView() {
		return textView;
	}



	public void setTextView(String textView) {
		this.textView = textView;
	}



	public View(int idView, String textView) {
		super();
		this.idView = idView;
		this.textView = textView;
	}



	@Override
	public String toString() {
		return "View [idView=" + idView + ", textView=" + textView + "]";
	}



	public View() {
		super();
	}




}
