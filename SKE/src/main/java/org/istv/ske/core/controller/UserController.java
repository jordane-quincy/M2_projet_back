package org.istv.ske.core.controller;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.configuration.ApplicationConfig;
import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.exception.InternalException;
import org.istv.ske.core.service.AuthenticationServiceImpl;
import org.istv.ske.core.service.FormationService;
import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.service.UserService;
import org.istv.ske.core.utils.FieldReader;
import org.istv.ske.dal.entities.Formation;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.Remark;
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

import com.google.gson.JsonArray;
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

	private final String REGEX_EMAIL = "^[aA-zZ0-9]+.[aA-zZ0-9]+(@univ-valenciennes.fr|@etu.univ-valenciennes.fr)";

	/**
	 * création d'un utilisateur
	 * @param request with JSON
	 * @return user created
	 * @throws Exception
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public User create(HttpServletRequest request) throws Exception {
		
		// Récupération del'object JSON passé en paramètre par le front
		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();

		// 
		String email = FieldReader.readString(object, "userMail");
		String name = FieldReader.readString(object, "userName");
		String firstName = FieldReader.readString(object, "userFirstName");
		String password = FieldReader.readString(object, "password");
		Long birthday = FieldReader.readLong(object, "birthday");
		Long formationId = FieldReader.readLong(object, "formationId");
		String question = FieldReader.readString(object, "question");
		String answer = FieldReader.readString(object, "answer");
		List<String> skills = FieldReader.readStringArray(object, "skills");
		String phoneNumber = FieldReader.readString(object, "phoneNumber");

		// Vérification de la conformité de l'email
		if (!email.matches(REGEX_EMAIL))
			// Si non conforme, erreur 400 retournée
			throw new BadRequestException("L'email fourni ne correspond pas a la regex requise");

		// Vérification de l'unicité de email
		if (userService.emailAlreadyExists(email)) {
			// Si l'email est déjà utilisé, erreur 400 retournée
			throw new BadRequestException("Cet email a déjà servi a créer un compte");
		}

		Formation formation = formationService.findFormationById(formationId);
		// Vérification de l'existance de la formation dans la base
		if (formation == null)
			// Si la formation n'est pas en base, erreur 400 retournée
			throw new BadRequestException("Cette formation n'existe pas");

		// Création d'un utilisateur
		try {
			// Instanciation de la question secrète
			SecretQuestion secretQuestion = new SecretQuestion(question, answer);
			// Instanciation du user
			User created = userService.createUser(email, name, firstName, password, birthday, formation, secretQuestion,
					skills, phoneNumber);
			// Génération du token lié à l'utilisateur
			String token = new BigInteger(32 * 4, random).toString(16);
			userService.setToken(created, token);

			// Préparation de l'eMail d'activation
			String msgMail = System.lineSeparator() + System.lineSeparator()
					+ "veuillez activer votre compte en cliquant sur ce lien : ";
			Email emailActivation = new Email(EmailType.ACTIVATION_EMAIL);
			emailActivation.setContenuMail(msgMail);
			emailActivation.setDestinataire(created);
			emailActivation.setObjet("Activation de votre compte SKE");
			emailActivation.setExpediteur(null);
			emailActivation.setUrlActivationAccount("https://clemscode.ovh/account_certification/certify/" + token);
			// Envoie de l'eMail d'activation
			emailClient.sendEmail(emailActivation);
			
			// utilisateur créé en retour
			return created;
		} catch (Exception e) {
			e.printStackTrace();
			// Erreur 500 si un problème est survenu lors de la création d'u utilisateur
			throw new InternalException("Erreur lors de la création de l'utilisateur : " + e.getMessage());
		}

	}
	
	/**
	 * suppresion d'un utilisateur
	 * @param request with JSON contening id
	 * @return JSON with response if success
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json")
	public String delete(HttpServletRequest request) throws Exception {
		// Récupération de l'ID de l'utilisateur en fonction du Token
		Long userId = tokenService.getUserIdByToken(request);
		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();
		// Récupératon de l'utiliasteur en fonction de l'ID
		User user = userService.getUser(userId);
		// Récupératon du password situé dans le JSON passé en paramètre
		String password = FieldReader.readString(object, "password");
		try {
			// Vérification de la validité du password
			if (!AuthenticationServiceImpl.chiffrer(password).equals(user.getUserPassword())) {
				// Si le password est incorrecte, Erreur 400
				throw new BadRequestException("Mot de passe erroné");
			}
			// Suppression de l'utilisateur en base
			userService.deleteUser(userId);
			// Suppression du Token
			tokenService.deleteTokenForUserId(userId);

			// JSON_SUCCESS en retour
			return ApplicationConfig.JSON_SUCCESS;
		} catch (Exception e) {
			// Si une erreur est parvenue lors de la suppression d'un utilisateur, erreur 500
			throw new BadRequestException("Impossible de supprimer l'user : " + e.getMessage());
		}
	}

	/**
	 * création d'un utilisateur
	 * @param request with JSON
	 * @return user updated
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
	public User update(HttpServletRequest request) throws Exception {
		// Récupération de l'ID de l'utilisateur en fonction du Token
		Long userId = tokenService.getUserIdByToken(request);
	
		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();
		// Récupératon de l'utiliasteur en fonction de l'ID
		User user = userService.getUser(userId);
		
		// Récupération de tous les champs contenu dans le JSON passé en paramètre
		String validatePassword = FieldReader.readString(object, "validatePassword");
		String name = FieldReader.readString(object, "userName");
		String firstName = FieldReader.readString(object, "userFirstName");
		String phoneNumber = FieldReader.readString(object, "phoneNumber");
		Long birthday = FieldReader.readLong(object, "birthday");
		Long formationId = FieldReader.readLong(object, "formationId");
		List<String> skills = FieldReader.readStringArray(object, "skills");
		String password = null;

		if (FieldReader.existField(object, "password"))
			password = FieldReader.readString(object, "password");

		// Vérification de la validité du password
		if (!AuthenticationServiceImpl.chiffrer(validatePassword).equals(user.getUserPassword()))
			// SI le password est incorecte, erreur 400
			throw new BadRequestException("Mauvais mot de passe de validation.");

		Formation formation = formationService.findFormationById(formationId);
		// Vérification de l'existance de la formation dans la base
		if (formation == null)
			// Si la formation n'est pas en base, erreur 400 retournée
			throw new BadRequestException("Cette formation n'existe pas");

		try {
			User updated = userService.updateUser(userId, name, firstName, birthday, formation, skills, phoneNumber);
			if (password != null)
				userService.setPassword(updated.getUserMail(), password);
			return updated;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalException("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
		}

	}

	/**
	 * liste de users
	 * @return liste des users
	 * @throws Exception
	 */
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

	/**
	 * get user by ID
	 * @param ID
	 * @return user
	 * @throws Exception
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
	public User get(@PathVariable(required = true) Long id) throws Exception {
		User user = userService.getUser(id);
		if (user != null)
			return user;
		else
			throw new BadRequestException("Cet id d'utilisateur n'existe pas");
	}

	/**
	 * Methode qui renvoie la question secrete d'un utilisateur pour mettre à jour le password
	 * @param request with JSON contening email of user
	 * @return Question Secrète
	 * @throws Exception
	 */
	@RequestMapping(value = "/askResetPassword", method = RequestMethod.POST, produces = "application/json")
	public SecretQuestion getSecretQuestion(HttpServletRequest request) throws Exception {
		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();
		// Récupération de l'email de l'utilisateur passé en paramètre dans un JSON
		String email = FieldReader.readString(object, "email");

		// Vérification de la validité de l'email
		if (!email.matches(REGEX_EMAIL))
			// Vérification de la validité de l'email
			throw new BadRequestException("L'email fourni ne correspond pas au regex requis");

		// Récupération de l'utilisateur en fonction de l'email
		User user = userService.getUserByUserMail(email);
		
		// Vérification qu'un utilisateur existe bien avec ce mail
		if (user == null)
			// Si l'utilisateur n'existe pas, erreur 400
			throw new BadRequestException("Cet email ne permet pas de retrouver un utilisateur");

		// Récupération de la question secrete de l'utilisateur
		SecretQuestion secretQuestion = user.getQuestion();

		// Si la question existe
		if (secretQuestion != null) {
			// Renvoie de la question
			return secretQuestion;
		} else {
			// sinon, erreur 500
			throw new InternalException("Question secrète non trouvée");
		}

	}

	/**
	 * Methode qui change le password si la réponse est bonne
	 * @param request with JSON contening response and new password
	 * @return JSON with response if success
	 * @throws Exception
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST, produces = "application/json")
	public String respondToQuestion(HttpServletRequest request) throws Exception {

		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();
		
		// Récupération des différent paramètres dans le JSON
		String email = FieldReader.readString(object, "email");
		String password = FieldReader.readString(object, "password");
		String answer = FieldReader.readString(object, "answer");

		// Vérification de la validité de l'email
		if (!email.matches(REGEX_EMAIL))
			// Vérification de la validité de l'email
			throw new BadRequestException("L'email fourni ne correspond pas au regex requis");

		// Récupération de l'utilisateur en fonction de l'email
		User user = userService.getUserByUserMail(email);

		// Vérification qu'un utilisateur existe bien avec ce mail
		if (user == null)
			// Si l'utilisateur n'existe pas, erreur 400
			throw new BadRequestException("Cet email ne permet pas de retrouver un utilisateur");

		// Récupération de la question secrete de l'utilisateur
		SecretQuestion secretQuestion = user.getQuestion();

		// Si la question n'existe pas, erreur 400
		if (secretQuestion == null)
			throw new BadRequestException("Question secrète non trouvée");

		// Si la réponse correpond
		if (secretQuestion.getAnswer().equals(answer)) {
			//reset password
			userService.setPassword(email, password);
			return ApplicationConfig.JSON_SUCCESS;
		} else {
			//Sinon,erreur 500
			throw new InternalException("Mauvaise réponse à la question");
		}

	}
	
	/**
	 * get current user
	 * @param request contening token
	 * @return id
	 * @return firstname
	 * @return lastname
	 * @throws Exception
	 */
	// Methode qui renvoie les information de l'utilisateur courant
	@RequestMapping(value = "/current", method = RequestMethod.GET, produces = "application/json")
	public String Usercurrent(HttpServletRequest request) throws Exception {
		// Récupération de l'ID en fonction du Token
		Long userId = tokenService.getUserIdByToken(request);
		// Récupération de l'utilisateur en fonction de l'ID
		User user = userService.getUser(userId);
		// Construction du JSON de retour
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", user.getId());
		jsonObject.addProperty("firstname", user.getUserFirstName());
		jsonObject.addProperty("lastname", user.getUserName());
		// Envoie du JSON
		return jsonService.stringify(jsonObject);
	}

	/**
	 * Méthode qui renvoie l'intégralité des avis d'un utilisateur
	 * @param request with JSON contening email of user
	 * @return liste des avis
	 * @return note moyenne
	 * @throws Exception
	 */
	@RequestMapping(value = "/opinions", method = RequestMethod.POST, produces = "application/json")
	public String getOpinions(HttpServletRequest request) throws Exception {
		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();
		// Récupération de l'email de l'utilisateur dans le JSON
		String email = FieldReader.readString(object, "email");
		// Récupération de l'utilisateur en base en fonction de son email
		User user = userService.getUserByUserMail(email);
		
		// récupération de la liste de ses offres
		Collection<Offer> offers = user.getOffers();

		Iterator<Offer> iterator = offers.iterator();

		int countRemark = 0;
		double averageMark = 0;
		int sumMark = 0;

		JsonArray offersArray = new JsonArray();

		// On parcour la liste des offres
		while (iterator.hasNext()) {
			Offer offer = (Offer) iterator.next();
			List<Remark> remarks = offer.getRemarks();
			Iterator<Remark> iteratorRemark = remarks.iterator();
			// Pour chaque offre, on parcours la liste des avis
			while (iteratorRemark.hasNext()) {
				Remark remark = (Remark) iteratorRemark.next();
				JsonObject remarkObject = new JsonObject();
				// On récupère les informations liées à l'avis
				remarkObject.addProperty("offerTitle", offer.getTitle());
				remarkObject.addProperty("remark", remark.getText());
				remarkObject.addProperty("grade", remark.getGrade());
				offersArray.add(remarkObject);
				// On fait la somme des notes des avis
				sumMark += remark.getGrade();
				// On incrémente de compteur d'avis
				countRemark++;
			}
		}

		// Moyenne des avis
		if (countRemark > 0) {
			averageMark = (double) Math.round(((double) sumMark / (double) countRemark) * 100) / 100;
		}

		// On renvoie un JSON avec la liste des avis et la note moyenne liée à l'utilisateur
		JsonObject result = new JsonObject();
		result.addProperty("averageMark", averageMark);
		result.add("remarks", offersArray);

		return jsonService.stringify(result);
	}

	/**
	 * Methode qui renvoie le nombre de credits  d'un uitilisateur
	 * @param request with JSON contening token
	 * @return credits
	 * @throws Exception
	 */
	@RequestMapping(value = "/credit", method = RequestMethod.GET, produces = "application/json")
	public String getCredit(HttpServletRequest request) throws Exception {
		// récupération de l'ID en fonction du Token
		Long userId = tokenService.getUserIdByToken(request);
		// Récupératoin de l'utilisateur en fonction de l'ID
		User user = userService.getUser(userId);
		// Construction du JSON de retour
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("credit", user.getCredit());
		return jsonService.stringify(jsonObject);
	}
}
