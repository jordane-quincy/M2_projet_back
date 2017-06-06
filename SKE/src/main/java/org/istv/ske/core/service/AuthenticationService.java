package org.istv.ske.core.service;

public interface AuthenticationService {

	boolean authenticate(String email, String password) throws Exception;
	
	
	
}
