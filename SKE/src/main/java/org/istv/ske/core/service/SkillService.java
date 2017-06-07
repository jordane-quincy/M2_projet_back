package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.entities.Skill;
import org.istv.ske.dal.entities.User;

public interface SkillService {

	public Skill createSkill(User user, String description, List<User> validators);
	public Skill updateSkill(User user, String description, List<User> validators);
	public void deleteSkill(Skill skill);
	public List<Skill> getAll();
}
