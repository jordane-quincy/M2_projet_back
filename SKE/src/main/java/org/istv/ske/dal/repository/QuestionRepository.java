package org.istv.ske.dal.repository;

import org.istv.ske.dal.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer>{

}
