package org.istv.ske.dal.service;

import java.util.ArrayList;
import java.util.List;

import org.istv.ske.dal.entities.Domain;
import org.istv.ske.dal.entities.Subject;
import org.istv.ske.dal.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomainServiceImpl implements DomainService {

	@Autowired
	private DomainRepository domainRepository;
	
	@Override
	public List<Domain> findAll() {
		List<Domain> domains = new ArrayList<>();
		for(Domain d : domainRepository.findAll()) {
			domains.add(d);
		}
		return domains;
	}

}
