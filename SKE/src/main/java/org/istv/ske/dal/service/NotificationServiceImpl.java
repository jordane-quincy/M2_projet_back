package org.istv.ske.dal.service;

import org.istv.ske.dal.entities.Notification;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Quentin on 07/06/2017.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;


    @Override
    public Notification createNotification(User user, String title, String content, String type) {
        Notification notification = new Notification();
        notification.setType(type);
        notification.setTitle(title);
        notification.setContent(content);
        notification.setUser(user);
        notificationRepository.save(notification);
        return notification;
    }

    @Override
    public void deleteNotification(Long notificationID) {
        notificationRepository.delete(notificationID);
    }

    @Override
    public List<Notification> getAll() {
        return (List<Notification>) notificationRepository.findAll();
    }

    @Override
    public Notification updateNotification(Long notificationID, String title, String content, String type, String createDate) {
        Notification notification = notificationRepository.findOne(notificationID);

        notification.setTitle(title);
        notification.setContent(content);
        notification.setCreationDate((java.sql.Date) new Date(createDate));
        notification.setType(type);

        notificationRepository.save(notification);

        return notification;
    }

    @Override
    public Notification getNotification(Long notificationID) {
        return notificationRepository.findOne(notificationID);
    }
}
