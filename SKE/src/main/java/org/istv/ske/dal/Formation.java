package org.istv.ske.dal;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "formation")
public class Formation {

	@Id
	@GeneratedValue
	private int id;
	
	private String level;
	
	private String name;

	@OneToMany(mappedBy = "formation")
	private Collection<User> users;

	public Formation() {
		// TODO Auto-generated constructor stub
	}

	public Formation(String level, String name, Collection<User> users) {
		this.level = level;
		this.name = name;
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
