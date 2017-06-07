package org.istv.ske.dal.service;

import java.util.List;

import org.istv.ske.dal.entities.Formation;
import org.istv.ske.dal.entities.SecretQuestion;
import org.istv.ske.dal.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	public User createUser(String email, String name, String firstName, String password, Long birthday, Formation formation, SecretQuestion secretQuestion);
	
	public void deleteUser(Long id);
	
	public List<User> getAll();
	
	public User getUser(Long userID);
	
	public User updateUser(Long id, String email, String name, String firstName, String password, Long birthday, Formation formation, SecretQuestion secretQuestion);
	
	public List<User> getUserByToken(String token);
}
