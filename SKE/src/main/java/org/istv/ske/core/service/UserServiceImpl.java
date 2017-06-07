package org.istv.ske.core.service;

import java.util.Date;
import java.util.List;

import org.istv.ske.dal.Formation;
import org.istv.ske.dal.User;
import org.istv.ske.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
	}

	@Override
	public List<User> getAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getUser(Long userID) {
		return userRepository.findOne(userID);
	}

	@Override
	public User createUser(String email, String name, String firstName, String password, Long birthday,
			String formationName, String formationLevel) {
		//TODO :est-ce qu'il faut concerver les champs foramtionName, formationLevel ?
		
		User user = new User(5, email, password, name, firstName, new Date(birthday), null, null, null, null, null);
		userRepository.save(user);
		return null;
	}

	@Override
	public User updateUser(Long id, String email, String name, String firstName, String password, Long birthday,
			String formationName, String formationLevel) {
		
		//TODO :est-ce qu'il faut concerver les champs foramtionName, formationLevel ?
		User user = userRepository.findOne(id);
		user.setBirthday(new Date(birthday));
		user.setUserMail(email);
		user.setUserName(name);
		user.setUserFirstName(firstName);
		user.setUserPassword(password);
		
		userRepository.save(user);
		return user;
	}

}
