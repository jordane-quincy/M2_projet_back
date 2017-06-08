package org.istv.ske.core.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User authenticate(String email, String password) throws Exception {
		User user = userRepository.findByUserMail(email);
		if (user == null) {
			throw new BadRequestException("Cet e-mail n'existe pas");
		}
		if (!user.getUserPassword().equals(chiffrer(password))) {
			throw new BadRequestException("Mot de passe incorrect");
		}
		// if(user.getToken() != null) {
		// throw new BadRequestException("Veuillez activer votre compte via le
		// mail d'activation");
		// }
		return user;
	}

	private static SecretKeySpec getKey(String secretKey) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		try {
			return new SecretKeySpec(digest.digest(new String(secretKey.getBytes(), "UTF8").getBytes()), "AES");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String chiffrer(String s) {
		String encrypted = null;
		try {
			// Instantiate the cipher
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, getKey("testduhash"));
			// Récupère la clé secrète
			byte[] cipherText = cipher.doFinal(s.getBytes("ISO-8859-1"));
			encrypted = new String(cipherText);
		} catch (Exception e) {
			System.out.println("Impossible to encrypt with AES algorithm: string=(" + s + ")");
		}
		return encrypted;
	}

}
