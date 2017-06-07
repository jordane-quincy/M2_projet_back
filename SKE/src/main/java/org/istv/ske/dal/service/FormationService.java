package org.istv.ske.dal.service;

import java.util.List;

import org.istv.ske.dal.entities.Formation;
import org.springframework.stereotype.Service;

@Service
public interface FormationService {
	
	public Formation createFormation(String name, String level);
	public void deleteFormation(Long id);
	public Formation updateFormation(Long id, String name, String level);
	public List<Formation> getAll();
	public Formation findFormationByID(Long id);
	
	

}
