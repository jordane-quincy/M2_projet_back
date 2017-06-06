package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.Domain;
import org.istv.ske.dal.Subject;
import org.istv.ske.dal.repository.DomainRepository;
import org.istv.ske.dal.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SearchServiceImpl {

	@Autowired
	DomainRepository domain_repository;
	
	@Autowired
	SubjectRepository subject_repository;
	
	@Transactional
	public List<Domain> findAllDomain() {
		return (List<Domain>) domain_repository.findAll();
	}
	
	public List<Subject> findbyId(int idDomain)
	{
		return (List<Subject>) subject_repository.findAll();
	}


}
