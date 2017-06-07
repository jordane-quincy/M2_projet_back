package org.istv.ske.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.exception.InternalException;
import org.istv.ske.core.service.JsonService;
<<<<<<< HEAD
//import org.istv.ske.dal.entities.Subject;
=======
import org.istv.ske.dal.entities.Formation;
>>>>>>> 32ae65b18c023992426c15968f7081ef6d78818f
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.service.FormationService;
import org.istv.ske.dal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	JsonService jsonService;

	@Autowired
	UserService userService;
<<<<<<< HEAD

	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json", produces = "Application/json")
	public User create(HttpServletRequest request) throws Exception {

=======
	
	@Autowired
	FormationService formationService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public User create(HttpServletRequest request) throws Exception{
		
>>>>>>> 32ae65b18c023992426c15968f7081ef6d78818f
		String email = null;
		String name = null;
		String firstName = null;
		String password = null;
		Long birthday = null;
<<<<<<< HEAD
		String formationName = null;
		String formationLevel = null;

=======
		Long formationID = null;
		
		User  user = null;
		Formation formation = null;
		
>>>>>>> 32ae65b18c023992426c15968f7081ef6d78818f
		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			email = content.get("email").getAsString();
			name = content.get("name").getAsString();
			firstName = content.get("firstName").getAsString();
			password = content.get("password").getAsString();
			birthday = content.get("birthday").getAsLong();
<<<<<<< HEAD
			formationName = content.get("formationName").getAsString();
			formationLevel = content.get("formationLevel").getAsString();

=======
			formationID = content.get("formationID").getAsLong();		
>>>>>>> 32ae65b18c023992426c15968f7081ef6d78818f
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requête invalide");
		}
<<<<<<< HEAD

		User user = null;
=======
		
>>>>>>> 32ae65b18c023992426c15968f7081ef6d78818f
		try {
			formation = formationService.findFormationByID(formationID);
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requête invalide : formation non reconnue");
		}
		
		
		try {
			user = userService.createUser(email, name, firstName, password, birthday, formation);
		} catch (Exception e) {
			throw new InternalException("Erreur lors de la création de l'utilisateur");
		}

		return user;

	}
<<<<<<< HEAD

	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.POST, produces = "Application/json")
	public String delete(HttpServletRequest request, @PathVariable(required = true) Long userId) throws Exception {

=======
	
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE, produces = "application/json")
	public String delete(
			HttpServletRequest request, 
			@PathVariable(required=true) Long userId) throws Exception {
		
>>>>>>> 32ae65b18c023992426c15968f7081ef6d78818f
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
<<<<<<< HEAD

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = "Application/json")
	public User update(HttpServletRequest request, @PathVariable(required = true) Long id) throws Exception {

=======
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = "application/json")
	public User update(HttpServletRequest request,
			@PathVariable(required=true) Long id) throws Exception{
		
>>>>>>> 32ae65b18c023992426c15968f7081ef6d78818f
		String email = null;
		String name = null;
		String firstName = null;
		String password = null;
		Long birthday = null;
<<<<<<< HEAD
		String formationName = null;
		String formationLevel = null;

=======
		Long formationID = null;
		
		User updatedUser = null;
		Formation formation = null;
		
>>>>>>> 32ae65b18c023992426c15968f7081ef6d78818f
		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			email = content.get("email").getAsString();
			name = content.get("name").getAsString();
			firstName = content.get("firstName").getAsString();
			password = content.get("password").getAsString();
			birthday = content.get("birthday").getAsLong();
<<<<<<< HEAD
			formationName = content.get("formationName").getAsString();
			formationLevel = content.get("formationLevel").getAsString();

=======
			formationID = content.get("formationID").getAsLong();		
>>>>>>> 32ae65b18c023992426c15968f7081ef6d78818f
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requête invalide");
		}
<<<<<<< HEAD

		User updatedUser = null;

		try {
			updatedUser = userService.updateUser(id, email, name, firstName, password, birthday, formationName,
					formationLevel);
=======
		
		try {
			formation = formationService.findFormationByID(formationID);
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requête invalide : formation non reconnue");
		}
		
		try {
			updatedUser = userService.updateUser(id, email, name, firstName, password, birthday, formation);
>>>>>>> 32ae65b18c023992426c15968f7081ef6d78818f
		} catch (Exception e) {
			throw new InternalException("Erreur lors de la mise à jour de l'utilisateur");
		}

		return updatedUser;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<User> list() throws Exception {

		List<User> list = null;

		try {
			list = userService.getAll();
		} catch (Exception e) {
			throw new InternalException("Erreur lors de la recherche des utilisateurs");
		}

		return list;
	}

}
