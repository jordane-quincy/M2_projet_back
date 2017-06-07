package org.istv.ske.core.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.service.SubscriptionService;
import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.entities.Appointment.AppointmentStatus;
import org.istv.ske.dal.repository.AppointmentRepository;
import org.istv.ske.dal.repository.UserRepository;
import org.istv.ske.dal.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

@RequestMapping("/subscribe")
@RestController
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OfferService offerService;
	
	@Autowired
	private JsonService jsonService;
	
	@Autowired
	private AppointmentRepository appointmentRepository;

	@RequestMapping(value = { "/subscription" }, method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody boolean subsciption(HttpServletRequest request) {

		// String s = "{\"IdOffer\": 1,\"Token\": \"untoken\"}";

		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			final String idOffer = content.get("IdOffer").getAsString();
			//TODO find user by token
			User user = userRepository.findOne(1L);
			Offer offer = offerService.getOffer(Long.valueOf(idOffer));
			
			Appointment app = new Appointment(offer, user,new Date() ,AppointmentStatus.PENDING);
			
			subscriptionService.subscription(app);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@RequestMapping(value = { "/unsubscription" }, method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody boolean unsubsciption(HttpServletRequest request) {
		try {
			JsonObject content = jsonService.parse(request.getReader()).getAsJsonObject();
			final String idOffer = content.get("IdOffer").getAsString();
			
			Appointment app = appointmentRepository.findOne(Long.valueOf(idOffer));
			app.setStatus(AppointmentStatus.CANCELLED);
			if (subscriptionService.subscription(app))
				return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
