package org.istv.ske.dal.service;

import java.util.List;

import org.istv.ske.dal.entities.Subject;

public interface SubjectService {
	
	List<Subject> findSubjectsByDomainId(Long id);
	
}
