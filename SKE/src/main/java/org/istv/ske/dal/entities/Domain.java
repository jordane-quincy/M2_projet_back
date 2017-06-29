package org.istv.ske.dal.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * La table Domain liste les grands domaines dans lesquels un utilisateur peut
 * proposer une compétence.
 * 
 * @param id
 *            Identifiant du domaine.
 * @param domainName
 *            Le nom du domaine.
 * @param offer
 *            Liste des offres proposées par tous les utilisateur pour un
 *            domaine spécifique.
 */
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

	/**
	 * Constucteur utilisé dans la partie core pour créér un domaine.
	 * 
	 * @param domainName
	 * @param offer
	 */
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