package org.istv.ske.security;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.dal.entities.User;

public interface TokenService {
	
	String createToken(User user);
	
	void onRequest(String token) throws Exception;
	
	Long getUserIdByToken(HttpServletRequest request) throws Exception;
	
	void deleteTokenForUserId(Long userId);
	
}
