package org.istv.ske.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.service.UserService;
import org.istv.ske.dal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	private JsonParser parser = new JsonParser();
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json", produces = "Application/json")
	public User create(HttpServletRequest request){
		
		User  user = null;
		
		try {
			JsonObject content = parser.parse(request.getReader()).getAsJsonObject();
			final String email = content.get("email").getAsString();
			final String name = content.get("email").getAsString();
			final String firstName = content.get("email").getAsString();
			final String password = content.get("email").getAsString();
//			final String name = content.get("email").getAsString();
//			final String name = content.get("email").getAsString();
//			final String name = content.get("email").getAsString();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
		
//		String email = "";
//		User  user= userService.createUser(email, name, firstName, password, birthday, formationName, formationLevel);

	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "Application/json")
	public void delete(@RequestParam(name = "email", required = true)String email){
		userService.deleteUser(email);
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
		List<User> list = userService.getAll();
		return list;
	}
	
}
