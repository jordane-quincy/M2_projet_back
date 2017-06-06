package org.istv.ske.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	private JsonParser parser = new JsonParser();
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@RequestMapping(value = {"/connect"}, method = RequestMethod.POST)
	public String authenticate(HttpServletRequest request) throws Exception {
		throw new BadRequestException("Ta gueule");
//		JsonObject object = parser.parse(request.getReader()).getAsJsonObject();
//		String email = object.get("email").getAsString();
//		
//		
//		return null;
	}
	
	
	
}
