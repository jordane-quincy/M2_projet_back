package org.istv.ske.dal.service;

import java.util.List;

import org.istv.ske.dal.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountCertificationServiceImpl implements AccountCertificationService {

	@Autowired
	private UserService userService;

	@Override
	public User activate(String token) {
		//List<User> userService.getUserByToken(token);
		//User user = .get(0);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isActivated(long userID) {
		User user = userService.getUser(userID);
		return user.getToken() != null;

	}

}
