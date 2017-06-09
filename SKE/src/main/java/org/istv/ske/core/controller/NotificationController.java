package org.istv.ske.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.istv.ske.configuration.ApplicationConfig;
import org.istv.ske.core.exception.BadRequestException;
import org.istv.ske.core.service.NotificationService;
import org.istv.ske.core.service.UserService;
import org.istv.ske.dal.entities.Notification;
import org.istv.ske.dal.entities.Notification.NotificationStatus;
import org.istv.ske.dal.entities.User;
import org.istv.ske.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserService userService;

	@Autowired
	private NotificationService notificationService;

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET, produces = "application/json")
	public List<Notification> list(HttpServletRequest request) throws Exception {
		Long userId = tokenService.getUserIdByToken(request);
		User user = userService.getUser(userId);
		return notificationService.findNotificationByUserByOrderByCreationDateDesc(user);
	}

	@RequestMapping(value = {
			"/markasread/{notificationId}" }, method = RequestMethod.POST, produces = "application/json")
	public String markAsRead(HttpServletRequest request, @PathVariable(required = true) Long notificationId)
			throws Exception {
		Long userId = tokenService.getUserIdByToken(request);
		Notification notification = notificationService.findNotificationById(notificationId);
		if (notification == null)
			throw new BadRequestException("Cette notification n'existe pas");
		if (notification.getUser().getId() != userId)
			throw new BadRequestException("Cette notification ne vous appartient pas");
		if (notification.getStatus() == NotificationStatus.READ)
			throw new BadRequestException("Cette notification a déjà été lue");
		notificationService.asRead(notificationId);
		return ApplicationConfig.JSON_SUCCESS;
	}

}
