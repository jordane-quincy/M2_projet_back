package org.istv.ske.dal.repository;

import org.istv.ske.dal.entities.Remark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemarkRepository extends CrudRepository<Remark, Long> {

}
