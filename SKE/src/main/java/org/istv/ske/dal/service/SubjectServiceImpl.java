package org.istv.ske.dal.service;

import java.util.List;

import org.istv.ske.dal.entities.Subject;
import org.istv.ske.dal.repository.DomainRepository;
import org.istv.ske.dal.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private DomainRepository domainRepository;

	@Override
	public List<Subject> findSubjectsByDomainId(Long id) {
		return subjectRepository.findByDomain(domainRepository.findOne(id));
	}
	
}
