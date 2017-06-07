package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.entities.Skill;

public interface SkillService {

	public Skill createSkill(String label);
	
	public Skill updateSkill(Long skillId, String label);
	
	public void deleteSkill(Long skillId);
	
	public List<Skill> findAll();
	
}
