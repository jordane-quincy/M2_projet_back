package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	public User createUser(String email, String name, String firstName, String password, String birthday, String formationName, String formationLevel);
	public void deleteUser(int id);
	public User updateUser(User user);
	public List<User> getAll();
	public User updateUser(String email, String fieldName, String value);
	public User getUser(int userID);
	
}
