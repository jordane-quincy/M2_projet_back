package org.istv.ske.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.service.UserService;
import org.istv.ske.dal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	JsonService jsonService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json", produces = "Application/json")
	public User create(HttpServletRequest request) throws Exception{
		
		String email = null;
		String name = null;
		String firstName = null;
		String password = null;
		String birthday = null;
		String formationName = null;
		String formationLevel = null;
		
		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			email = content.get("email").getAsString();
			name = content.get("name").getAsString();
			firstName = content.get("firstName").getAsString();
			password = content.get("password").getAsString();
			birthday = content.get("birthday").getAsString();
			formationName = content.get("formationName").getAsString();
			formationLevel = content.get("formationLevel").getAsString();
		
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requête invalide");		
		}
		
		User  user = null;
		
		try {
			user = userService.createUser(email, name, firstName, password, birthday, formationName, formationLevel);
			
		} catch (Exception e) {
			//TODO: handle exception
		}
		
		return user;

	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "Application/json")
	public String delete(HttpServletRequest request) throws Exception{
		
		int id = (Integer) null;
		JsonObject response = new JsonObject();
		
		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			id = content.get("id").getAsInt();
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requête invalide");
		}
		
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			response.addProperty("ok", false);
			response.addProperty("message", e.getMessage());
			return jsonService.stringify(response);
		}
		
		response.addProperty("ok", true);
		return jsonService.stringify(response);
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "Application/json")
	public User update(@RequestParam(name = "email", required = true)String email,
			@RequestParam(name = "field", required = true)String fieldName,
			@RequestParam(name = "value", required = true)String value){
		User updatedUser = userService.updateUser(email, fieldName, value);
		return updatedUser;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "Application/json")
	public List<User> list(){
		
		List<User> list = null;
		
		try {
			list = userService.getAll();	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	
}
