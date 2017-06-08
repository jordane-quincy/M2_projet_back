package org.istv.ske.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.configuration.ApplicationConfig;
import org.istv.ske.core.exception.BadRequestException;
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
		
		User user = authenticationService.authenticate(email, password);
		String token = tokenService.createToken(user);
		
		JsonObject response = new JsonObject();
		response.addProperty("ok", true);
		response.addProperty("token", token);
		return jsonService.stringify(response);
	}
	
	@RequestMapping(value = {"/disconnect"}, method = RequestMethod.POST, produces="application/json")
	public String disconnect(HttpServletRequest request) throws Exception {
		Long userId = tokenService.getUserIdByToken(request);
		tokenService.deleteTokenForUserId(userId);
		return ApplicationConfig.JSON_SUCCESS;
	}
	
	
	
}
