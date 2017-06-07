package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	public User createUser(String email, String name, String firstName, String password, Long birthday, String formationName, String formationLevel);
	public void deleteUser(Long id);
	public User updateUser(User user);
	public List<User> getAll();
	public User getUser(int userID);
	public User updateUser(Long id, String email, String name, String firstName, String password, Long birthday, String formationName, String formationLevel);
	
}
