package org.istv.ske.dal.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Chaque utilisateur inscrit dans l'application dispose d'une formation, d'un
 * parcours scolaire qui lui est propre.
 * 
 * @param id
 *            Identifiant de la formation.
 * @param level
 *            Niveau de la formation (ex: M1, L3, ...).
 * @param name
 *            Nom de la formation.
 * @param users
 *            Liste des utilisateurs de la formation.
 */
@Entity
public class Formation {

	@Id
	@GeneratedValue
	private Long id;

	private String level;

	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "formation")
	private Collection<User> users;

	public Formation() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constucteur utilisé dans la partie core pour créér un une formation.
	 * 
	 * @param level
	 * @param name
	 * @param users
	 */
	public Formation(String level, String name, Collection<User> users) {
		this.level = level;
		this.name = name;
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

}
