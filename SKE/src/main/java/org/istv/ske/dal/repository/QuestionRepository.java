package org.istv.ske.dal.repository;

import org.istv.ske.dal.SecretQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<SecretQuestion, Integer>{

}
