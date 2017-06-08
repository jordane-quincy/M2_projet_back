package org.istv.ske.core.service;

import java.util.Date;
import java.util.List;

import org.istv.ske.dal.entities.Notification;
import org.istv.ske.dal.entities.User;

public interface NotificationService {

    Notification createNotification(String title, String content, String type, User user);

    void deleteNotification(Notification notification);

    Notification updateNotification(Notification notification, String title, String content, String type, User user);

    List<Notification> getAll();
    
    Notification findNotificationById(Long id);

   List<Notification> findNotificationByUser(User user);
}
