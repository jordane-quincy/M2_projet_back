package org.istv.ske.dal.service;

import java.util.ArrayList;
import java.util.List;

import org.istv.ske.dal.entities.Formation;
import org.istv.ske.dal.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormationServiceImpl implements FormationService {
	
	@Autowired
	FormationRepository formationRepository;

	@Override
	public Formation createFormation(String name, String level) {
		Formation formation = new Formation();
		formation.setName(name);
		formation.setLevel(level);
		
		formationRepository.save(formation);
		
		return formation;
	}

	@Override
	public void deleteFormation(Long id) {
		formationRepository.delete(id);

	}

	@Override
	public Formation updateFormation(Long id, String name, String level) {
		Formation formation  = formationRepository.findOne(id);
		
		formation.setName(name);
		formation.setLevel(level);
		
		formationRepository.save(formation);
		
		return formation;
	}

	@Override
	public List<Formation> getAll() {
		List<Formation> formations = new ArrayList<>();
		for(Formation f : formationRepository.findAll()) {
			formations.add(f);
		}
		return formations;
	}

	@Override
	public Formation findFormationByID(Long id) {
		return formationRepository.findOne(id);
	}

}
