package org.istv.ske.core;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("/player/{name}")
	public String getAccountsByEmail(@PathVariable("name") String name) {
		return name;
	} 
	
	@RequestMapping("/player2/{name}")
	public String test(@PathVariable("name") String name) {
		return name;
	} 

}
