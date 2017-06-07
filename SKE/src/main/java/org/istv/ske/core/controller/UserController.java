package org.istv.ske.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.exception.InternalException;
import org.istv.ske.core.service.JsonService;
//import org.istv.ske.dal.entities.Subject;
import org.istv.ske.dal.entities.User;
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

	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json", produces = "Application/json")
	public User create(HttpServletRequest request) throws Exception {

		String email = null;
		String name = null;
		String firstName = null;
		String password = null;
		Long birthday = null;
		String formationName = null;
		String formationLevel = null;

		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			email = content.get("email").getAsString();
			name = content.get("name").getAsString();
			firstName = content.get("firstName").getAsString();
			password = content.get("password").getAsString();
			birthday = content.get("birthday").getAsLong();
			formationName = content.get("formationName").getAsString();
			formationLevel = content.get("formationLevel").getAsString();

		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requête invalide");
		}

		User user = null;
		try {
			user = userService.createUser(email, name, firstName, password, birthday, formationName, formationLevel);
		} catch (Exception e) {
			throw new InternalException("Erreur lors de la création de l'utilisateur");
		}

		return user;

	}

	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.POST, produces = "Application/json")
	public String delete(HttpServletRequest request, @PathVariable(required = true) Long userId) throws Exception {

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

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = "Application/json")
	public User update(HttpServletRequest request, @PathVariable(required = true) Long id) throws Exception {

		String email = null;
		String name = null;
		String firstName = null;
		String password = null;
		Long birthday = null;
		String formationName = null;
		String formationLevel = null;

		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			email = content.get("email").getAsString();
			name = content.get("name").getAsString();
			firstName = content.get("firstName").getAsString();
			password = content.get("password").getAsString();
			birthday = content.get("birthday").getAsLong();
			formationName = content.get("formationName").getAsString();
			formationLevel = content.get("formationLevel").getAsString();

		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requête invalide");
		}

		User updatedUser = null;

		try {
			updatedUser = userService.updateUser(id, email, name, firstName, password, birthday, formationName,
					formationLevel);
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
