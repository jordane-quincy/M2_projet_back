package org.istv.ske.core.controller;

import java.util.List;

import org.istv.ske.core.service.UserService;
import org.istv.ske.dal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "Application/json")
	public User create(@RequestParam(name = "email", required = true)String email,
			@RequestParam(name = "name", required = true)String name,
			@RequestParam(name = "firstName", required = true)String firstName,
			@RequestParam(name = "password", required = true)String password,
			@RequestParam(name = "birthday", required = true)String birthday,
			@RequestParam(name = "formationName", required = true)String formationName,
			@RequestParam(name = "formationLevel", required = true)String formationLevel){
		User  user= userService.createUser(email, name, firstName, password, birthday, formationName, formationLevel);
		return user;
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
