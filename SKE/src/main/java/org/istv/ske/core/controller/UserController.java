package org.istv.ske.core.controller;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.configuration.ApplicationConfig;
import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.exception.InternalException;
import org.istv.ske.core.service.FormationService;
import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.service.UserService;
import org.istv.ske.core.utils.FieldReader;
import org.istv.ske.dal.entities.Formation;
import org.istv.ske.dal.entities.SecretQuestion;
import org.istv.ske.dal.entities.User;
import org.istv.ske.messages.common.EmailClient;
import org.istv.ske.messages.enums.EmailType;
import org.istv.ske.messages.model.Email;
import org.istv.ske.security.TokenService;
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
	private JsonService jsonService;

	@Autowired
	private UserService userService;

	@Autowired
	private FormationService formationService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private EmailClient emailClient;

	private SecureRandom random = new SecureRandom();

	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public User create(HttpServletRequest request) throws Exception {

		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();

		String email = FieldReader.readString(object, "userMail");
		String name = FieldReader.readString(object, "userName");
		String firstName = FieldReader.readString(object, "userfirstName");
		String password = FieldReader.readString(object, "password");
		Long birthday = FieldReader.readLong(object, "birthday");
		Long formationId = FieldReader.readLong(object, "formationId");
		String question = FieldReader.readString(object, "question");
		String answer = FieldReader.readString(object, "answer");
		List<String> skills = FieldReader.readStringArray(object, "skills");

		if (!email.matches("^[aA-zZ0-9]+.[aA-zZ0-9]+@(univ-valenciennes.fr|etu.univ-valenciennes.fr)"))
			throw new BadRequestException("L'email fourni ne correspond pas a la regex requise");

		if (userService.emailAlreadyExists(email)) {
			throw new BadRequestException("Cet email a déjà servi a créer un compte");
		}

		Formation formation = formationService.findFormationById(formationId);
		if (formation == null)
			throw new BadRequestException("Cette formation n'existe pas");

		try {
			SecretQuestion secretQuestion = new SecretQuestion(question, answer);
			User created = userService.createUser(email, name, firstName, (password), birthday, formation,
					secretQuestion, skills);
			String token = new BigInteger(32 * 4, random).toString(16);
			userService.setToken(created, token);

			String msgMail = System.lineSeparator() + System.lineSeparator()
					+ "veuillez activer votre compte en cliquant sur ce lien : ";
			Email emailActivation = new Email(EmailType.ACTIVATION_EMAIL);
			emailActivation.setContenuMail(msgMail);
			emailActivation.setDestinataire(created);
			emailActivation.setObjet("Activation de votre compte SKE");
			emailActivation.setExpediteur(null);
			emailActivation.setUrlActivationAccount("http://10.4.132.150:8080/account_certification/certify/" + token);
			emailClient.sendEmail(emailActivation);

			return created;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalException("Erreur lors de la création de l'utilisateur : " + e.getMessage());
		}

	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json")
	public String delete(HttpServletRequest request) throws Exception {
		Long userId = tokenService.getUserIdByToken(request);
		try {
			userService.deleteUser(userId);
			tokenService.deleteTokenForUserId(userId);
			return ApplicationConfig.JSON_SUCCESS;
		} catch (Exception e) {
			throw new BadRequestException("Impossible de supprimer l'user : " + e.getMessage());
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
	public User update(HttpServletRequest request) throws Exception {

		Long userId = tokenService.getUserIdByToken(request);

		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();

		String password = FieldReader.readString(object, "password");
		Long formationId = FieldReader.readLong(object, "formationId");
		List<String> skills = FieldReader.readStringArray(object, "skills");

		Formation formation = formationService.findFormationById(formationId);
		if (formation == null)
			throw new BadRequestException("Cette formation n'existe pas");

		try {
			User updated = userService.updateUser(userId, password, formation, skills);
			return updated;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalException("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
		}

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

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
	public User get(@PathVariable(required = true) Long id) throws Exception {
		User user = userService.getUser(id);
		if (user != null)
			return user;
		else
			throw new BadRequestException("Cet id d'utilisateur n'existe pas");
	}

	@RequestMapping(value = "/askResetPassword", method = RequestMethod.POST, produces = "application/json")
	public SecretQuestion getSecretQuestion(HttpServletRequest request) throws Exception {
		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();

		String email = FieldReader.readString(object, "email");

		if (!email.matches("^[aA-zZ0-9]+.[aA-zZ0-9]+@(univ-valenciennes.fr|etu.univ-valenciennes.fr)"))
			throw new BadRequestException("L'email fourni ne correspond pas au regex requis");

		User user = userService.getUserByUserMail(email);

		if (user == null)
			throw new BadRequestException("Cet email ne permet pas de retrouver un utilisateur");

		SecretQuestion secretQuestion = user.getQuestion();

		if (secretQuestion != null) {
			return secretQuestion;
		} else {
			throw new InternalException("Question secrète non trouvée");
		}

	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST, produces = "application/json")
	public String respondToQuestion(HttpServletRequest request) throws Exception {

		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();

		String email = FieldReader.readString(object, "email");
		String password = FieldReader.readString(object, "password");
		String answer = FieldReader.readString(object, "answer");

		if (!email.matches("^[aA-zZ0-9]+.[aA-zZ0-9]+@(univ-valenciennes.fr|etu.univ-valenciennes.fr)"))
			throw new BadRequestException("L'email fourni ne correspond pas au regex requis");

		User user = userService.getUserByUserMail(email);

		if (user == null)
			throw new BadRequestException("Cet email ne permet pas de retrouver un utilisateur");

		SecretQuestion secretQuestion = user.getQuestion();

		if (secretQuestion == null)
			throw new BadRequestException("Question secrète non trouvée");

		if (secretQuestion.getAnswer().equals(answer)) {
			userService.setPassword(email, password);
			return ApplicationConfig.JSON_SUCCESS;
		} else {
			throw new InternalException("Mauvaise réponse à la question");
		}

	}
	
	@RequestMapping(value = "/current", method = RequestMethod.GET, produces = "application/json")
	public String Usercurrent(HttpServletRequest request) throws Exception {
		Long userId = tokenService.getUserIdByToken(request);
		User user = userService.getUser(userId);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", user.getId());
		jsonObject.addProperty("firstname", user.getUserFirstName()); 
		jsonObject.addProperty("lastname", user.getUserName());
		return jsonService.stringify(jsonObject);
	}
}
