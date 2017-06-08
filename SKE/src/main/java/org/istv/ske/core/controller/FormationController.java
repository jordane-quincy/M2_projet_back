package org.istv.ske.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.configuration.ApplicationConfig;
import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.service.FormationService;
import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.utils.FieldReader;
import org.istv.ske.dal.entities.Formation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

@RestController
@RequestMapping("/formation")
public class FormationController {
	
	@Autowired
	private JsonService jsonService;
	
	@Autowired
	private FormationService formationService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public Formation create(HttpServletRequest request) throws Exception {
		
		JsonObject object = jsonService.parse(request.getReader()).getAsJsonObject();

		String name = FieldReader.readString(object, "name");
		String level = FieldReader.readString(object, "level");
		
		Formation created = formationService.createFormation(name, level);
		return created;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<Formation> list() throws Exception {
		return formationService.getAll();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public String delete(@PathVariable(required=true) Long id) throws Exception {
		try {
			formationService.deleteFormation(id);
			return ApplicationConfig.JSON_SUCCESS;
		} catch (Exception e) {
			throw new BadRequestException("Cet id de formation n'existe pas");
		}
	}
	
}
