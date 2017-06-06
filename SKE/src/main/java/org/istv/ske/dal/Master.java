package org.istv.ske.dal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "master")
public class Master {
	
	@Id
	private int masterId;

}
