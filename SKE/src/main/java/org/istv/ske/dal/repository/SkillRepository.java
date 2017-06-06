package org.istv.ske.dal.repository;

import org.istv.ske.dal.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer>{

}
