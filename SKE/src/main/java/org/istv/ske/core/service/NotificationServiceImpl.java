package org.istv.ske.core.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.Notification;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationRepository notificationRepository;
	@Override
	public Notification createNotification(String title, String content, String type, Date creationDate, User user) {
		Notification notification=  new Notification();
		notification.setContent(content);
		notification.setCreationDate(creationDate);
		notification.setTitle(title);
		notification.setType(type);
		notification.setUser(user);
		notificationRepository.save(notification);
		return notification;
	}

	@Override
	public void deleteNotification(Notification notification) {
		notificationRepository.delete(notification);
		
	}

	@Override
	public Notification updateNotification( Notification notification, String title, String content,String type, Date creationDate, User user) {
		Notification notificationFound =  notificationRepository.findOne(notification.getId());
		notificationFound.setContent(content);
		notificationFound.setCreationDate(creationDate);
		notificationFound.setTitle(title);
		notificationFound.setType(type);
		notificationFound.setUser(user);
		notificationRepository.save(notificationFound);
		return notificationFound;
	}

	@Override
	public List<Notification> getAll() {
		List<Notification> notifications = new ArrayList<>();
		for(Notification n : notificationRepository.findAll()) {
			notifications.add(n);
		}
		return notifications;
	}

}
