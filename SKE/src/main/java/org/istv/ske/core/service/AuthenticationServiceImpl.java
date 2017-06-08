package org.istv.ske.core.service;

import java.util.List;

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
			throw new RuntimeException("Cet e-mail n'existe pas");
		}
		if(!user.getUserPassword().equals(password)) {
			throw new RuntimeException("Mot de passe incorrect");
		}
		return user;
	}

}
