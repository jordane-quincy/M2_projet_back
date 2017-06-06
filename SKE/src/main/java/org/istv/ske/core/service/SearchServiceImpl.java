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
public class SearchServiceImpl implements SearchService {

	@Autowired
	DomainRepository domain_repository;
	
	@Autowired
	SubjectRepository subject_repository;
	
	@Override
	public List<Domain> findAllDomain() {
		return (List<Domain>) domain_repository.findAll();
	}
	
	@Override
	public Subject findbyId(int idDomain)
	{
		//TODO
		return subject_repository.findOne(idDomain);
	}


}
