package org.istv.ske.core;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping(value = { "/player" }, method = RequestMethod.GET, headers = "Accept=*/*", produces = "Application/json")
	@ResponseBody
	public String getAccountsByEmail() {
		String s = "[{\"Nom\": \"test\"},{\"Type\": \"\"}]";
		return s;
	} 


	@RequestMapping("/profile/{id}")
	public String getProfile(@PathVariable("id") String idUser){
		//TODO
		return idUser;
	}
	
	@RequestMapping("/credit_user/{id}")
	public int getCredit(@PathVariable("id") String idUser){
		//TODO
		return 0;
	}
	
	@RequestMapping("/my_offer/{id}")
	public int getMyOffer(@PathVariable("id") String idUser){
		//TODO
		return 0;
	}
	
	
	@RequestMapping("/transac/{idd}/{idc}/{much}")
	public int transaction(@PathVariable("idd") String idDebiteur,//
			@PathVariable("idc") String idCrediteur,//
			@PathVariable("much") int montant){
		//TODO
		return 0;
	}
	
	@RequestMapping("/matiere/{id}")
	public int getListMatiereOfDomain(@PathVariable("id") String id){
		//TODO
		return 0;
	}
	
	@RequestMapping("/domain")
	public int getListDomain(){
		//TODO
		return 0;
	}
}
