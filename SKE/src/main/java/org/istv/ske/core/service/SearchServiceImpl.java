package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.entities.Domain;
import org.istv.ske.dal.entities.Subject;
import org.istv.ske.dal.repository.DomainRepository;
import org.istv.ske.dal.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	DomainRepository domainRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Override
	public List<Domain> findAllDomains() {
		return (List<Domain>) domainRepository.findAll();
	}
	
	@Override
	public List<Subject> findSubjectsByDomain(Long domainId) {
		Domain domain = domainRepository.findOne(domainId);
		return subjectRepository.findByDomain(domain);
	}

}
