package org.istv.ske.dal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;

import lombok.Data;
@Data
@Entity

public class Role {
	
	@Id
	@GeneratedValue
	private int idRole;



}
