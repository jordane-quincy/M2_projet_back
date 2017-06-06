package org.istv.ske.dal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "master")
public class Master {
private int masterId;

	@Id
	private int id;

}
