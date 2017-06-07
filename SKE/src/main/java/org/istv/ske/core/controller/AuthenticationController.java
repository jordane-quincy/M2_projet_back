package org.istv.ske.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.configuration.ApplicationConfig;
import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.service.AuthenticationService;
import org.istv.ske.core.service.JsonService;
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
		String email = null;
		String password = null;
		try {
			JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();
			email = object.get("email").getAsString();
			password = object.get("password").getAsString();
		} catch(Exception e) {
			throw new BadRequestException(e.getMessage());
		}
		
		User user = null;
		JsonObject response = new JsonObject();
		
		try {
			user = authenticationService.authenticate(email, password);
		} catch (Exception e) {
			response.addProperty("ok", false);
			response.addProperty("message", e.getMessage());
			return jsonService.stringify(response);
		}
		
		try {
			String token = tokenService.createToken(user);
			JsonObject content = new JsonObject();
			content.addProperty("token", token);
			response.addProperty("ok", true);
			response.add("content", content);
			return jsonService.stringify(response);
		} catch (Exception e) {
			response.addProperty("ok", false);
			response.addProperty("message", e.getMessage());
			return jsonService.stringify(response);
		}
	}
	
	@RequestMapping(value = {"/disconnect"}, method = RequestMethod.POST, produces="application/json")
	public String disconnect(HttpServletRequest request) throws Exception {
		Long userId = tokenService.getUserIdByToken(request);
		tokenService.deleteTokenForUserId(userId);
		return ApplicationConfig.JSON_SUCCESS;
	}
	
	
	
}
