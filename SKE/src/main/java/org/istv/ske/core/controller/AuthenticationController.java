package org.istv.ske.core.controller;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.configuration.ApplicationConfig;
import org.istv.ske.core.service.AuthenticationService;
import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.utils.FieldReader;
import org.istv.ske.dal.entities.Skill;
import org.istv.ske.dal.entities.User;
import org.istv.ske.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private JsonService jsonService;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private TokenService tokenService;

	/**
	 * connexion d'un utilisateur
	 * 
	 * @param request
	 *            with JSON contening email and password
	 * @return JSON contenant les informations du user
	 * @throws Exception
	 */
	@RequestMapping(value = { "/connect" }, method = RequestMethod.POST, produces = "application/json")
	public String authenticate(HttpServletRequest request) throws Exception {

		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();

		// Récupération des champs email et password
		String email = FieldReader.readString(object, "email");
		String password = FieldReader.readString(object, "password");

		// Authentification du user
		User user = authenticationService.authenticate(email, password);
		// Génération du token
		String token = tokenService.createToken(user);

		// Création de l'objet JSON correspondant au user
		JsonObject userJson = new JsonObject();
		userJson.addProperty("id", user.getId());
		userJson.addProperty("userFirstName", user.getUserFirstName());
		userJson.addProperty("userMail", user.getUserMail());
		userJson.addProperty("userName", user.getUserName());
		userJson.addProperty("credit", user.getCredit());
		userJson.addProperty("birthday", user.getBirthday().getTime());
		userJson.addProperty("role", user.getRole().name());

		JsonObject formation = new JsonObject();
		formation.addProperty("id", user.getFormation().getId());
		formation.addProperty("name", user.getFormation().getName());
		formation.addProperty("level", user.getFormation().getLevel());

		userJson.add("formation", formation);

		JsonArray skills = new JsonArray();
		for (Entry<Skill, Boolean> entry : user.getSkills().entrySet()) {
			JsonObject skill = new JsonObject();
			skill.addProperty("id", entry.getKey().getId());
			skill.addProperty("label", entry.getKey().getLabel());
			skill.addProperty("validated", entry.getValue());
			skills.add(skill);
		}
		userJson.add("skills", skills);
		userJson.addProperty("phoneNumber", user.getPhoneNumber());
		JsonObject response = new JsonObject();
		response.addProperty("ok", true);
		response.addProperty("token", token);
		response.add("user", userJson);
		return jsonService.stringify(response);
	}

	/**
	 * deconnexion d'un utilisateur
	 * 
	 * @param request
	 *            with token
	 * @return success JSON
	 * @throws Exception
	 */
	@RequestMapping(value = { "/disconnect" }, method = RequestMethod.POST, produces = "application/json")
	public String disconnect(HttpServletRequest request) throws Exception {
		Long userId = tokenService.getUserIdByToken(request);
		tokenService.deleteTokenForUserId(userId);
		return ApplicationConfig.JSON_SUCCESS;
	}

}
