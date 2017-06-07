package org.istv.ske.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.Skill;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SkillServiceImpl implements SkillService {
	@Autowired
	private SkillRepository skillRepository ;

	@Override
	public Skill createSkill(User user, String label, int mark, List<User> validators) {
		Skill skill = new Skill();
		skill.setLabel(label);
		skill.setUser(user);
		skill.setGrade(mark);
		skill.setValidators(validators);
		skillRepository.save(skill);
		return skill;
	}

	@Override
	public Skill updateSkill(User user, String label, int mark, List<User> validators) {
		  Skill skill = skillRepository.findOne(user.getId());
		  skill.setUser(user);
		  skill.setLabel(label);
		  skill.setGrade(mark);
		  skill.setValidators(validators);
		  skillRepository.save(skill);
		return skill;
		
	}

	@Override
	public void deleteSkill(Skill skill) {
		skillRepository.delete(skill);
	}
	@Override
	public List<Skill> getAll() {
		List<Skill> skills = new ArrayList<>();
		for(Skill s : skillRepository.findAll()) {
			skills.add(s);
		}
		return skills;
	}
}
