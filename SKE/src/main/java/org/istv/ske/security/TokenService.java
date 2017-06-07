package org.istv.ske.security;

import org.istv.ske.dal.entities.User;

public interface TokenService {
	
	String createToken(User user);
	
	boolean isExpired(String token);
	
}
