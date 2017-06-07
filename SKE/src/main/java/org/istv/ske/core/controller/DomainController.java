package org.istv.ske.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.configuration.ApplicationConfig;
import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.service.DomainService;
import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.service.SearchService;
import org.istv.ske.core.utils.FieldReader;
import org.istv.ske.dal.entities.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

@RestController
@RequestMapping("/domain")
public class DomainController {

	@Autowired
	private DomainService domainService;

	@Autowired
	SearchService search_service;

	@Autowired
	private JsonService jsonService;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<Domain> findAllDomains() throws Exception {
		try {
			List<Domain> domains = domainService.findAll();
			return domains;
		} catch (Exception e) {
			throw new RuntimeException("Impossible de récupérer la liste de domaines");
		}
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public Domain create(HttpServletRequest request) throws Exception {
		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();
		
		String name = FieldReader.readString(object, "name");
		Domain created = domainService.createDomain(name);
		
		return created;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public String delete(@PathVariable(required=true) Long id) throws Exception {
		try {
			domainService.deleteDomain(id);
			return ApplicationConfig.JSON_SUCCESS;
		} catch (Exception e) {
			throw new BadRequestException("Cet id de domaine n'existe pas");
		}
	}
}

