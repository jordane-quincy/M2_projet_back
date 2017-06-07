package org.istv.ske.dal.repository;

import java.util.List;

import org.istv.ske.dal.entities.Domain;
import org.istv.ske.dal.entities.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {

	List<Subject> findByDomain(Domain domain);
	
}
