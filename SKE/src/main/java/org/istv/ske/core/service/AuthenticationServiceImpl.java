package org.istv.ske.core.service;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User authenticate(String email, String password) throws Exception {
		User user = userRepository.findByUserMail(email);
		if(user == null) {
			throw new BadRequestException("Cet e-mail n'existe pas");
		}
		if(!user.getUserPassword().equals(password)) {
			throw new BadRequestException("Mot de passe incorrect");
		}
		if(user.getToken() != null) {
			throw new BadRequestException("Veuillez activer votre compte via le mail d'activation");
		}
		return user;
	}

}
