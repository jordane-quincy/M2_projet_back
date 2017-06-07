package org.istv.ske.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.service.SearchService;
import org.istv.ske.dal.entities.Domain;
import org.istv.ske.dal.entities.Subject;
import org.istv.ske.dal.service.DomainService;
import org.istv.ske.dal.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private DomainService domainService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	SearchService search_service;
	
	@Autowired
	private JsonService jsonService;

	
	@RequestMapping(value = "/domains", method = RequestMethod.POST, produces = "application/json")
	public List<Domain> findAllDomains() throws Exception {
		try {
			List<Domain> domains = domainService.findAll();
			return domains; 
		} catch (Exception e) {
			throw new RuntimeException("Impossible de récupérer la liste de domaines");
		}
	}
	
	@RequestMapping(value = "/subject/{domainId}", method = RequestMethod.GET, produces = "application/json")
	public List<Subject> getSubject(
			HttpServletRequest request, 
			@PathVariable(required=true) Long domainId) throws Exception {
		try {
			List<Subject> subjects = subjectService.findSubjectsByDomainId(domainId);
			return subjects;
		} catch (Exception e) {
			throw new Exception("Impossible de récupérer la liste de matières pour le domaine n°" + domainId);
		}
	}
}








