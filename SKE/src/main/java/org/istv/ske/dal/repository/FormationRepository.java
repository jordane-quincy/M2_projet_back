package org.istv.ske.dal.repository;

import org.istv.ske.dal.entities.Formation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepository extends CrudRepository<Formation, Long> {

}
