package org.istv.ske.core.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.istv.ske.configuration.ApplicationConfig;
import org.istv.ske.core.service.AuthenticationService;
import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.utils.FieldReader;
import org.istv.ske.dal.entities.User;
import org.istv.ske.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private JsonService jsonService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = {"/connect"}, method = RequestMethod.POST, produces="application/json")
	public String authenticate(HttpServletRequest request) throws Exception {
		
		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();
		
		String email = FieldReader.readString(object, "email");
		String password = FieldReader.readString(object, "password");
		
		User user = authenticationService.authenticate(email,  chiffrer(password));
		String token = tokenService.createToken(user);
		
		JsonObject response = new JsonObject();
		response.addProperty("ok", true);
		response.addProperty("token", token);
		return jsonService.stringify(response);
	}
	
	private static SecretKeySpec getKey(String secretKey) {
	    MessageDigest digest = null;
	    try {
	        digest = MessageDigest.getInstance("MD5");
	 
	    } catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException(e);
	    }
	 
	    try {
	        return new SecretKeySpec(digest.digest(new String(secretKey.getBytes(),"UTF8").getBytes()), "AES");
	    }
	    catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	private static String chiffrer(String s) {
        String encrypted = null;
        try {
           // Instantiate the cipher
           Cipher cipher = Cipher.getInstance("AES");
           cipher.init(Cipher.ENCRYPT_MODE, getKey("testduhash"));
           // Récupère la clé secrète
            byte[] cipherText = cipher.doFinal(s.getBytes("ISO-8859-1"));
            encrypted = new String(cipherText);
        }
        catch (Exception e) {
            System.out.println("Impossible to encrypt with AES algorithm");
        }
        return encrypted;
	}
	
	@RequestMapping(value = {"/disconnect"}, method = RequestMethod.POST, produces="application/json")
	public String disconnect(HttpServletRequest request) throws Exception {
		Long userId = tokenService.getUserIdByToken(request);
		tokenService.deleteTokenForUserId(userId);
		return ApplicationConfig.JSON_SUCCESS;
	}
	
	
	
}
