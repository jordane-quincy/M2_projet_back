package org.istv.ske.dal.service;

import java.util.List;

import org.istv.ske.dal.entities.Domain;
import org.istv.ske.dal.entities.User;

public interface DomainService {

	List<Domain> findAll();
	
	Domain createDomain(String DomainName);
}
