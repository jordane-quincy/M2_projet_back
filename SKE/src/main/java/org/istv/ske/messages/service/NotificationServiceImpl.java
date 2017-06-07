package org.istv.ske.messages.service;

import org.istv.ske.dal.Notification;
import org.istv.ske.dal.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Quentin on 07/06/2017.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;


    @Override
    public Notification createNotification(String title, String content, String type, String createDate) {
        return null;
    }

    @Override
    public void deleteNotification(Long notificationID) {

    }

    @Override
    public List<Notification> getAll() {
        return null;
    }

    @Override
    public Notification updateNotification(Notification notification) {
        return null;
    }

    @Override
    public Notification getNotification(Long notificationID) {
        return null;
    }
}
