package org.istv.ske.core.service;

import java.util.Date;
import java.util.List;

import org.istv.ske.dal.entities.Notification;
import org.istv.ske.dal.entities.User;

public interface NotificationService {

    public Notification createNotification(String title, String content, String type, User user);

    public void deleteNotification(Notification notification);

    public Notification updateNotification(Notification notification, String title, String content, String type, User user);

    public List<Notification> getAll();
    
    public Notification findNotificationById(Long id);

   public List<Notification> findNotificationByUser(User user);
}
