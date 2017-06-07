package org.istv.ske.security;

import java.util.HashMap;

import org.istv.ske.dal.entities.User;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.Timestamp;

@Service
public class TokenServiceImpl implements TokenService {

	private HashMap<Token, User> tokens = new HashMap<Token, User>();
	private SecureRandom random = new SecureRandom();

	/**
	 * Check if token exist
	 * 
	 * @param token
	 * @return
	 */
	public boolean isTokenExist(String token) {
		for (Token t : tokens.keySet()) {

			String value = t.getValue();
			System.out.println("value:" + value);

			if (value.equals(token)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Display token list
	 */
	public void displayTokensList() {

		for (Token t : tokens.keySet()) {

			String value = t.getValue();

			System.out.println("token_value:" + value);
		}

	}

	/**
	 * Generate random token
	 * 
	 * @return
	 */
	public String generateRandomToken() {
		return new BigInteger(130, random).toString(32);
	}

	/**
	 * create token
	 */
	@Override
	public String createToken(User user) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis() + (3600 * 2));

		try {
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}

		String tokenValue = "";

		do {
			tokenValue = generateRandomToken();
		} while (isTokenExist(tokenValue.toString()));

		Token token = new Token(timestamp, tokenValue.toString());

		tokens.put(token, user);

		this.displayTokensList();

		return tokenValue.toString();
	}

	/**
	 * Check if token is valid
	 * 
	 */
	@Override
	public boolean isExpired(String token) {

		Token getToken = new Token();

		for (Token t : tokens.keySet()) {
			// if token exist
			if (token.equals(t.getValue())) {
				
				// check if token expired
				Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

				// if expired
				if (currentTimestamp.after(getToken.getTimestamp())) {
					return false;
				} else {
					return false;
				}
			}
		}

		return false;
	}
}
