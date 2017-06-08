package org.istv.ske.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.dal.entities.Skill;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.SkillRepository;
import org.istv.ske.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl implements SkillService {
	
	@Autowired
	private SkillRepository skillRepository;
	private Skill skill;
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Skill createSkill(String label) {
		Skill skill = new Skill();
		skill.setLabel(label);
		skillRepository.save(skill);
		return skill;
	}

	@Override
	public Skill updateSkill(Long skillId, String label) {
		Skill skill = skillRepository.findOne(skillId);
		skill.setLabel(label);
		skillRepository.save(skill);
		return skill;
	}

	@Override
	public void deleteSkill(Long skillId) {
		skillRepository.delete(skillId);
	}
	
	@Override
	public List<Skill> findAll() {
		List<Skill> skills = new ArrayList<>();
		for(Skill s : skillRepository.findAll()) {
			skills.add(s);
		}
		return skills;
	}
	
	@Override
	public void createSkillsIfNotExists(List<String> skills) {
		skill = null;
		for(String skillLabel : skills) {
			if(skillRepository.findByLabel(skillLabel).equals(null))
			{
				skill.setLabel(skillLabel);
				skillRepository.save(skill);
			}
			
		}
	}

	@Override
	public Skill findByLabel(String label) {
	
		Skill skill = skillRepository.findByLabel(label) ;
			
		return skill;
	}

	@Override
	public void validateSkill(Long userId, Long skillId) throws Exception {
		User user = userService.getUser(userId);
		if(user == null)
			throw new BadRequestException("Cet utilisateur n'existe pas");
		
		for(Entry<Skill, Boolean> entry : user.getSkills().entrySet()) {
			if(entry.getKey().getId() == skillId) {
				if(entry.getValue()) {
					throw new BadRequestException("Ce skill a déja été validé");
				} else {
					entry.setValue(true);
					userRepository.save(user);
					return;
				}
			}
		}
		
		throw new BadRequestException("Cet utlisateur ne possede pas ce skill");
	}
	
	
}
