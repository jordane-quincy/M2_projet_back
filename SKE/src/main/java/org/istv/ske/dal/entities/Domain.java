package org.istv.ske.dal.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Domain {

	@Id
	@GeneratedValue
	private Long id;

	private String domainName;

	@OneToMany(mappedBy = "domain")
	private Collection<Offer> offer;

	public Domain() {

	}

	public Domain(String domainName, Collection<Offer> offer) {
		this.domainName = domainName;
		this.offer = offer;
	}

	public Collection<Offer> offer() {
		return offer;
	}

	public void setOffer(Collection<Offer> offer) {
		this.offer = offer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

}