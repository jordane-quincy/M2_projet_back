package org.istv.ske.core.service;

import org.istv.ske.dal.entities.User;

public interface AuthenticationService {

	User authenticate(String email, String password) throws Exception;
	
}
