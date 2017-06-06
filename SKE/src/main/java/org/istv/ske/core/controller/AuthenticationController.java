package org.istv.ske.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@RequestMapping("/connect")
	public String authenticate() {
		return null;
	}
	
}
