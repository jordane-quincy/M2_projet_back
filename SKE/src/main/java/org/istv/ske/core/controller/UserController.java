package org.istv.ske.core.controller;

import java.util.List;

import org.istv.ske.core.service.UserService;
import org.istv.ske.dal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "Application/json")
	public User create(String email, String name, String firstName, String password, String birthday, String formationName, String formationLevel){
		User  user= userService.createUser(email, name, firstName, password, birthday, formationName, formationLevel);
		return user;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "Application/json")
	public void delete(String email){
		userService.deleteUser(email);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "Application/json")
	public User update(User user){
		User updatedUser = userService.updateUser(user);
		return updatedUser;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "Application/json")
	public List<User> list(){
		List<User> list = userService.getAll();
		return list;
	}
	

}
