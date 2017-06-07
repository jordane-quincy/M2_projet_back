package org.istv.ske.dal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.istv.ske.dal.entities.Formation;
import org.istv.ske.dal.entities.SecretQuestion;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.entities.User.Role;
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
		List<User> users = new ArrayList<>();
		for(User u : userRepository.findAll()) {
			users.add(u);
		}
		return users;
	}

	@Override
	public User getUser(Long userID) {
		return userRepository.findOne(userID);
	}

	@Override
	public User createUser(String email, String name, String firstName, String password, Long birthday, Formation formation, SecretQuestion secretQuestion) {		
		Role role = (email.endsWith("@etu.univ-valenciennes.fr") ? Role.STUDENT : Role.TEACHER);
		User user = new User(5, email, password, name, firstName, new Date(birthday), null, null, role, formation, null, secretQuestion);
		userRepository.save(user);
		return user;
	}

	@Override
	public User updateUser(Long id, String email, String name, String firstName, String password, Long birthday,
			Formation formation, SecretQuestion secretQuestion) {
		User user = userRepository.findOne(id);
		user.setBirthday(new Date(birthday));
		user.setUserMail(email);
		user.setUserName(name);
		user.setUserFirstName(firstName);
		user.setUserPassword(password);
		user.setFormation(formation);
		user.setQuestion(secretQuestion);
		userRepository.save(user);
		return user;
	}

	@Override
	public List<User> getUserByToken(String token) {
		return userRepository.findByToken(token);
	}

}
