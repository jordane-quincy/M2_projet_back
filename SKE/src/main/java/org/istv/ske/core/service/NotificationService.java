package org.istv.ske.core.service;

import java.sql.Date;
import java.util.List;

import javax.persistence.ManyToOne;

import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.Notification;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;

public interface NotificationService {
public Notification createNotification( String title, String content,String type, Date creationDate, User user);

	public void deleteNotification( Notification notification);
	
	public Notification updateNotification(  Notification notification, String title, String content,String type, Date creationDate, User user);
	
	public List<Notification> getAll();
}
