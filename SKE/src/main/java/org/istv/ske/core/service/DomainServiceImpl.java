package org.istv.ske.core.service;

import java.util.ArrayList;
import java.util.List;

import org.istv.ske.dal.entities.Domain;
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
		for (Domain d : domainRepository.findAll()) {
			domains.add(d);
		}
		return domains;
	}
	
	@Override
	public Domain createDomain(String DomainName) {
		Domain Domain = new Domain();
		Domain.setDomainName(DomainName);
		domainRepository.save(Domain);
		return Domain;
	}

	@Override
	public void deleteDomain(Long id) {
		domainRepository.delete(id);
	}
	
	@Override
	public Domain findById(Long id) {
		return domainRepository.findOne(id);
	}
	
}
