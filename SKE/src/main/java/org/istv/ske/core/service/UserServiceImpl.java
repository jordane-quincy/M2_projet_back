package org.istv.ske.core.service;

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
		//TODO
		//userRepository.delete(id);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getUser(int userID) {
		return userRepository.findOne(userID);
	}

	@Override
	public User createUser(String email, String name, String firstName, String password, Long birthday,
			String formationName, String formationLevel) {
//		User user = new User();
//		Formation formation = new Formation(formationLevel, formationName);
//		
//		
//		user.setUserMail(email);
//		//TODO
//		//user.setBirthday(birthday);
//		user.setCredit(5);
//		user.setUserFirstName(firstName);
//		user.setUserName(name);
//		user.setUserPassword(password);
//		
//		user.setFormation(formation);
//		
//		
//		userRepository.save(user);
		return null;
	}

	@Override
	public User updateUser(Long id, String email, String name, String firstName, String password, Long birthday,
			String formationName, String formationLevel) {
		// TODO Auto-generated method stub
		return null;
	}

}
