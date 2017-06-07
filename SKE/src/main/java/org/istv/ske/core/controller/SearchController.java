package org.istv.ske.core.controller;

import java.util.List;

import org.istv.ske.core.service.SearchService;
import org.istv.ske.dal.Domain;
import org.istv.ske.dal.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController 
{

	@Autowired
	SearchService search_service;
	
	@RequestMapping(value = "/get/domain", method = RequestMethod.POST, headers = "Accept=*/*", produces = "Application/json")
	public List<Domain> ListDomain(){
		//TODO Get all Domains with  
		List<Domain> domain = search_service.findAllDomain();
		return domain;
	}
	
	@RequestMapping(value = "/read/subject", method = RequestMethod.POST, headers = "Accept=*/*", produces = "Application/json")
	public Subject getSubject(@RequestParam("domain") int idDomain){
		//TODO Get all subject with the idDomain  
		Subject subject = search_service.findbyId(idDomain);
		return subject;
	}
}
