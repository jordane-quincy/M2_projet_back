package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.Domain;
import org.istv.ske.dal.Subject;

public interface SearchService {

	// récupère tous les domaines
	public List<Domain> findAllDomain();
	
	// récupère tous les cours lié à un domaine
	public Subject findbyId(int idDomain);

}
