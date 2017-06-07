package org.istv.ske.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.exception.InternalException;
import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.service.SecretQuestionService;
import org.istv.ske.core.service.SkillService;
import org.istv.ske.dal.entities.Formation;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.service.FormationService;
import org.istv.ske.dal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	JsonService jsonService;

	@Autowired
	UserService userService;

	@Autowired
	FormationService formationService;
	
	@Autowired
	SecretQuestionService secretQuestionService;
	
	@Autowired
	SkillService skillService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public User create(HttpServletRequest request) throws Exception{

		String email = null;
		String name = null;
		String firstName = null;
		String password = null;
		Long birthday = null;
		Long formationID = null;
		String question = null;
		String answer = null;
		JsonArray skills = null;
		

		User  user = null;
		Formation formation = null;

		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			email = content.get("email").getAsString();
			name = content.get("name").getAsString();
			firstName = content.get("firstName").getAsString();
			password = content.get("password").getAsString();
			birthday = content.get("birthday").getAsLong();
			formationID = content.get("formationID").getAsLong();
			question = content.get("question").getAsString();
			answer = content.get("answer").getAsString();
			skills = content.get("skills").getAsJsonArray();
			for (int i = 0 ; i < skills.size(); i++) {
		        JsonObject skill = skills.get(i).getAsJsonObject();
		        skill.get("skillLabel").getAsString();
		        skill.get("skillMark").getAsInt();
		        skill.get("customSkill").getAsBoolean();
		    }
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requête invalide");	
		}

		if(email.matches("^[aA-zZ0-9]+.[aA-zZ0-9]+@(univ-valenciennes.fr|etu.univ-valenciennes.fr)")){

			try {
				formation = formationService.findFormationByID(formationID);
			} catch (Exception e) {
				throw new BadRequestException("Contenu de la requête invalide : formation non reconnue");
			}

			try {
				user = userService.createUser(email, name, firstName, password, birthday, formation);
//				secretQuestionService.createSecretQuestion(user, question, answer);
//				for (int i = 0 ; i < skills.size(); i++) {
//			        JsonObject skill = skills.get(i).getAsJsonObject();
//			        String label = skill.get("skillLabel").getAsString();
//			        int mark = skill.get("skillMark").getAsInt();
			        //TODO : ajouter les skills aux users
//			        Boolean custom = skill.get("customSkill").getAsBoolean();
//			        
//			        if (custom){
//			        	
//			        } else {
//			        	skillService.createSkill(user, label, mark, null);
//			        }
//			    }
			} catch (Exception e) {
				throw new InternalException("Erreur lors de la création de l'utilisateur");
			}

			return user;

		} else {
			throw new BadRequestException("adresse email invalide");
		}

	}

	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE, produces = "application/json")
	public String delete(
			HttpServletRequest request,
			@PathVariable(required=true) Long userId) throws Exception{

		JsonObject response = new JsonObject();

		try {
			userService.deleteUser(userId);
			response.addProperty("ok", true);
		} catch (Exception e) {
			response.addProperty("ok", false);
			response.addProperty("message", e.getMessage());
		}

		return jsonService.stringify(response);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = "application/json")
	public User update(HttpServletRequest request,
			@PathVariable(required=true) Long id) throws Exception{

		String email = null;
		String name = null;
		String firstName = null;
		String password = null;
		Long birthday = null;
		Long formationID = null;

		User updatedUser = null;
		Formation formation = null;

		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			email = content.get("email").getAsString();
			name = content.get("name").getAsString();
			firstName = content.get("firstName").getAsString();
			password = content.get("password").getAsString();
			birthday = content.get("birthday").getAsLong();
			formationID = content.get("formationID").getAsLong();		
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requête invalide");		
		}

		try {
			formation = formationService.findFormationByID(formationID);
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requête invalide : formation non reconnue");
		}

		try {
			updatedUser = userService.updateUser(id, email, name, firstName, password, birthday, formation);
		} catch (Exception e) {
			throw new InternalException("Erreur lors de la mise à jour de l'utilisateur");
		}



		return updatedUser;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<User> list() throws Exception{

		List<User> list = null;

		try {
			list = userService.getAll();	
		} catch (Exception e) {
			throw new InternalException("Erreur lors de la recherche des utilisateurs");
		}

		return list;
	}

}
