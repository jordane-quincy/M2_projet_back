package org.istv.ske.core.controller;

import java.util.List;

import org.istv.ske.core.service.SkillService;
import org.istv.ske.dal.entities.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
public class SkillController {
	
	@Autowired
	private SkillService skillService;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<Skill> list() throws Exception {
		return skillService.findAll();
	}
	
	@RequestMapping
	public String validateSkill() {
		return null;
	}
	
}
