package org.istv.ske.security;

import org.istv.ske.dal.User;

public interface TokenService {
	String createToken(User user);
	boolean isExpired(String token);
}
