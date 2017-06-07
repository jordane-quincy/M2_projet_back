package org.istv.ske.security;

import org.istv.ske.dal.entities.User;

public interface TokenService {
	
	String createToken(User user);
	
	void onRequest(String token) throws Exception;
	
}
