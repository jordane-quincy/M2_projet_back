package org.istv.ske.core.controller;

import org.istv.ske.core.service.AccountCertificationService;
import org.istv.ske.dal.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account_certification")
public class AccountCertificationController {


	@Autowired
	AccountCertificationService accountCertificationService;

	@RequestMapping(value = "/certify/{token}", method = RequestMethod.POST, produces = "application/json")
	private User certifyAccount(@PathVariable(required = true) String token) {

		User use = accountCertificationService.activate(token);

		return use;
	}
}
