package org.istv.ske.dal.service;

import org.istv.ske.dal.entities.Notification;
import org.istv.ske.dal.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Quentin on 07/06/2017.
 */
@Service
public interface NotificationService {

    public Notification createNotification(User user, String title, String content, String type);

    public void deleteNotification(Long notificationID);

    public List<Notification> getAll();

    public Notification updateNotification(Long notificationID, String title, String content, String type, String createDate);

    public Notification getNotification(Long notificationID);

}
