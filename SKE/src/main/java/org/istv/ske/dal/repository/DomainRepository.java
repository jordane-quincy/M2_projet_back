package org.istv.ske.dal.repository;

import org.istv.ske.dal.Domain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends CrudRepository<Domain, Integer>{

}
