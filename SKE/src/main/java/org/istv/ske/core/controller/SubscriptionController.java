package org.istv.ske.core.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.service.OfferService;
import org.istv.ske.core.service.SubscriptionService;
import org.istv.ske.core.service.UserService;
import org.istv.ske.dal.Appointment;
import org.istv.ske.dal.Offer;
import org.istv.ske.dal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RequestMapping("/subscribe/")
@RestController
public class SubscriptionController {

	@Autowired
	SubscriptionService subscriptionService;

	@Autowired
	UserService userSerivce;

	@Autowired
	OfferService offerService;
	
	private JsonParser parser = new JsonParser();

	@RequestMapping(value = { "/subscription" }, method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody boolean subsciption(HttpServletRequest request) {

		// String s = "{\"IdOffer\": 1,\"Token\": \"untoken\"}";

		try {
			JsonObject content = parser.parse(request.getReader()).getAsJsonObject();
			final String idOffer = content.get("IdOffer").getAsString();
			User user = userSerivce.getUser(1);
			Offer offer = offerService.getOffer(1);
			Appointment app = new Appointment(1, user,new Date() ,"pending");
			//app.setAppointmentDateTime(DateTime.now());
			
			subscriptionService.subscription(app);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@RequestMapping(value = { "/unsubscription" }, method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody boolean unsubsciption(HttpServletRequest request) {

		// String s = "{\"IdOffer\": 1,\"Token\": \"untoken\"}";

		try {
			JsonObject content = parser.parse(request.getReader()).getAsJsonObject();
			final String idOffer = content.get("IdOffer").getAsString();

			if (subscriptionService.unsubscription(null))
				return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
