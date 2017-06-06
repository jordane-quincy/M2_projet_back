package org.istv.ske.dal.repository;

import org.istv.ske.dal.View;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepository extends CrudRepository<View, Integer>{

}
