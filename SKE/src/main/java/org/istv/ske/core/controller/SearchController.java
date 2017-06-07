package org.istv.ske.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.service.SearchService;
import org.istv.ske.dal.Domain;
import org.istv.ske.dal.Subject;
import org.istv.ske.dal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

@RestController
@RequestMapping("/search")
public class SearchController 
{

	@Autowired
	SearchService search_service;
	
	@Autowired
	private JsonService jsonService;

	
	@RequestMapping(value = "/get/domain", method = RequestMethod.POST, headers = "Accept=*/*", produces = "Application/json")
	public String ListDomain(){
		//TODO Get all Domains with  
		JsonObject response = new JsonObject();
		try {
			List<Domain> domain = search_service.findAllDomain();
			return "ok"; 
		} catch (Exception e) {
			response.addProperty("ok", false);
			response.addProperty("message", e.getMessage());
			return jsonService.stringify(response);
		}
	}
	
	@RequestMapping(value = "/read/subject", method = RequestMethod.POST, headers = "Accept=*/*", produces = "Application/json")
	public String getSubject(HttpServletRequest request) throws BadRequestException{
		//TODO Get all subject with the idDomain  

		int idDomain = 0;
		try {

			JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();
			idDomain = object.get("idDomain").getAsInt();
			
		} catch (Exception e) {
			throw new BadRequestException("Contenu de la requête invalide");
		}

		JsonObject response = new JsonObject();
		
		try {
			Subject subject = search_service.findbyDomain(idDomain);
			if(subject != null){
				throw new RuntimeException("Ce numéro de domain n'existe pas");
			}
			return "ok"; 
			
		} catch (Exception e) {
			response.addProperty("ok", false);
			response.addProperty("message", e.getMessage());
			return jsonService.stringify(response);
		}
		

	}
}








