package org.istv.ske.dal.repository;

import org.istv.ske.dal.Remark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepository extends CrudRepository<Remark, Integer>{

}
