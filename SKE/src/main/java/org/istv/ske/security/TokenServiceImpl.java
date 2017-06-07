package org.istv.ske.security;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.istv.ske.dal.entities.User;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
	
	private static final int TOKEN_SIZE = 32;
	
	private static final int TOKEN_VALIDITY_MS = 1 * 60 * 1000;

	private Map<Token, Long> tokens = new HashMap<>();
	
	private SecureRandom random = new SecureRandom();

	public Token find(String token) {
		for (Token t : tokens.keySet()) {
			String value = t.getValue();
			if (value.equals(token))
				return t;
		}
		return null;
	}

	public void displayTokensList() {
		for (Token t : tokens.keySet()) {
			String value = t.getValue();
			System.out.println("token_value:" + value);
		}
	}

	public String generateRandomToken() {
		return new BigInteger(TOKEN_SIZE * 4, random).toString(16);
	}

	@Override
	public String createToken(User user) {
		String token = "";
		do {
			token = generateRandomToken();
		} while(find(token) != null);

		Token t = new Token(System.currentTimeMillis(), token);
		tokens.put(t, user.getId());

		return token;
	}
	
	@Override
	public void onRequest(String token) throws Exception {
		Token t = find(token);
		if(t == null)
			throw new RuntimeException("Le token " + token + " n'existe pas");
		
		if(System.currentTimeMillis() - t.timestamp > TOKEN_VALIDITY_MS) {
			tokens.remove(t);
			throw new RuntimeException("Le token " + token + " est expiré");
		}
		
		t.timestamp = System.currentTimeMillis();
	}
	
}
