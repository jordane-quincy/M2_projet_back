package org.istv.ske.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.configuration.ApplicationConfig;
import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.service.SkillService;
import org.istv.ske.core.service.UserService;
import org.istv.ske.core.utils.FieldReader;
import org.istv.ske.dal.entities.Skill;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.entities.User.Role;
import org.istv.ske.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

@RestController
@RequestMapping("/skill")
public class SkillController {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private SkillService skillService;
	
	@Autowired
	private JsonService jsonService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<Skill> list() throws Exception {
		return skillService.findAll();
	}
	
	@RequestMapping(value = "/validate", method = RequestMethod.POST, produces = "application/json")
	public String validateSkill(HttpServletRequest request) throws Exception {
		Long validatorId = tokenService.getUserIdByToken(request);
		User validator = userService.getUser(validatorId);
	
		if(validator.getRole() != Role.TEACHER)
			throw new BadRequestException("Seuls les enseignants peuvent valider les compétences");
		
		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();

		Long userId = FieldReader.readLong(object, "userId");
		Long skillId = FieldReader.readLong(object, "skillId");
		
		skillService.validateSkill(userId, skillId);
		
		return ApplicationConfig.JSON_SUCCESS;
	}
	
}
