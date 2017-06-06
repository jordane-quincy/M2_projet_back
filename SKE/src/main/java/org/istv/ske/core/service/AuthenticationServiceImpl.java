package org.istv.ske.core.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Override
	public boolean authenticate(String email, String password) throws Exception {
		
		return false;
	}

}
