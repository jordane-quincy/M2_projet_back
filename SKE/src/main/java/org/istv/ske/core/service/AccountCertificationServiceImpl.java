package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.dal.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCertificationServiceImpl implements AccountCertificationService {

	@Autowired
	private UserService userService;

	@Override
	public User activate(String token) throws BadRequestException {
		List<User> users = userService.getUserByToken(token);
		if(users.isEmpty()){
			throw new BadRequestException("Aucun user n'est affilié a ce token d'utilisation");
		}
		User user = users.get(0);		
		userService.setToken(user, null);
		System.out.println("[Activate User][Sucess] -- user : "+user.toString());
		return null;
	}

	@Override
	public boolean isActivated(long userID) {
		User user = userService.getUser(userID);
		return user.getToken() != null;

	}

}
