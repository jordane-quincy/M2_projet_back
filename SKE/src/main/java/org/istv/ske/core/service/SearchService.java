package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.entities.Domain;
import org.istv.ske.dal.entities.Subject;

public interface SearchService {

	public List<Domain> findAllDomains();
	
	public List<Subject> findSubjectsByDomain(Long domainId);
	
}
