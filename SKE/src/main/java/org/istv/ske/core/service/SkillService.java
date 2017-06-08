package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.dal.entities.Skill;

public interface SkillService {
	
	void createSkillsIfNotExists(List<String> skills);

	Skill createSkill(String label);
	
	Skill updateSkill(Long skillId, String label);
	
	void deleteSkill(Long skillId);
	
	List<Skill> findAll();
	
	Skill findByLabel(String label);
	
	void validateSkill(Long userId, Long skillId) throws BadRequestException;
}
