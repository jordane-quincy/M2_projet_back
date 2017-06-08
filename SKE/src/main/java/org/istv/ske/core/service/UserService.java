package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.entities.Formation;
import org.istv.ske.dal.entities.SecretQuestion;
import org.istv.ske.dal.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	User createUser(String email, String name, String firstName, String password, Long birthday, Formation formation, SecretQuestion secretQuestion, List<String> skills);
	
	void deleteUser(Long id);
	
	List<User> getAll();
	
	User getUser(Long userID);
	
	User updateUser(Long id, String password, Formation formation, List<String> skills);
	
	List<User> getUserByToken(String token);
	
	boolean emailAlreadyExists(String email);

	User getUserByUserMail(String email);

	void setPassword(String email, String password);

	User createUser(String email, String name, String firstName, String password, Long birthday, Formation formation,
			SecretQuestion secretQuestion, List<String> skills, String token);
}