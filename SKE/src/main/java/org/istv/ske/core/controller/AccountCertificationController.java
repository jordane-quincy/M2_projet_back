package org.istv.ske.core.controller;

import org.istv.ske.configuration.ApplicationConfig;
import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.service.AccountCertificationService;
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

	/**
	 * certification d'un token. Vérifie la validité d'un token
	 * 
	 * @param token
	 * @return JSON success
	 * @throws Exception
	 */
	@RequestMapping(value = "/certify/{token}", method = RequestMethod.GET, produces = "application/json")
	private String certifyAccount(@PathVariable(required = true) String token) throws BadRequestException {

		accountCertificationService.activate(token);

		return ApplicationConfig.JSON_SUCCESS;
	}
}
