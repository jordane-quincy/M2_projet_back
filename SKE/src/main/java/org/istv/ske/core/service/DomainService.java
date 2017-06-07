package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.entities.Domain;

public interface DomainService {

	List<Domain> findAll();
	
	Domain createDomain(String DomainName);

	void deleteDomain(Long id);
	
	Domain findById(Long id);
}
