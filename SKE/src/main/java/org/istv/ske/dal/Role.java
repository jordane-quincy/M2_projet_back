package org.istv.ske.dal;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	
	@Id
	@GeneratedValue
	private int idRole;

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public Role(int idRole) {
		super();
		this.idRole = idRole;
	}
	   @OneToMany(mappedBy="role")
private Collection<User> users;
	@Override
	public String toString() {
		return "Role [idRole=" + idRole + "]";
	}

	public Role() {
		super();
	}



}
