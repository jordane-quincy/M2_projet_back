package org.istv.ske.dal;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "domain")
public class Domain {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String domainName;
	
	@OneToMany(mappedBy="domain")
	private Collection<Subject> subjects;
	
	public Domain() {
		
	}

	public Domain(String domainName, Collection<Subject> subjects) {
		this.domainName = domainName;
		this.subjects = subjects;
	}

	public Collection<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Collection<Subject> subjects) {
		this.subjects = subjects;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
}