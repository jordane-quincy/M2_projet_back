package org.istv.ske.dal.repository;

import org.istv.ske.dal.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer>{

}
