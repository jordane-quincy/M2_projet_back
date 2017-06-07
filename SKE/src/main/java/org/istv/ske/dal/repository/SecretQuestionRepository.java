package org.istv.ske.dal.repository;

import org.istv.ske.dal.entities.SecretQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretQuestionRepository extends CrudRepository<SecretQuestion, Long> {

}
