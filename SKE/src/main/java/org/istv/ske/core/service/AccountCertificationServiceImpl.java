package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.dal.entities.User;
import org.istv.ske.messages.common.EmailClient;
import org.istv.ske.messages.enums.EmailType;
import org.istv.ske.messages.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCertificationServiceImpl implements AccountCertificationService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailClient emailClient;

	@Override
	public User activate(String token) throws BadRequestException {
		List<User> users = userService.getUserByToken(token);
		if(users.isEmpty()){
			throw new BadRequestException("Aucun user n'est affilié a ce token d'utilisation");
		}
		User user = users.get(0);		
		userService.setToken(user, null);
		System.out.println("[Activate User][Sucess] -- user : "+user.toString());
		Email emailActivation = new Email(EmailType.NOTIFICATION_EMAIL);
		emailActivation.setContenuMail("Votre compte SKE a bien été activé");
		emailActivation.setDestinataire(user);
		emailActivation.setObjet("Votre compte SKE a bien été activé");
		emailActivation.setExpediteur(null);
		emailActivation.setUrlActivationAccount(null);
		emailClient.sendEmail(emailActivation);
		return user;
	}

	@Override
	public boolean isActivated(long userID) {
		User user = userService.getUser(userID);
		return user.getToken() != null;

	}

}
