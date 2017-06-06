package org.istv.ske.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("subscribe")
public class SubscriptionController {

	
	@RequestMapping(value = { "/subscription" }, method = RequestMethod.POST, headers = "Accept=application/xml")
	public @ResponseBody boolean subsciption() {
		
		String s = "[{\"Nom\": \"test\"},{\"Type\": \"\"}]";
		
		return true;
	}

}
