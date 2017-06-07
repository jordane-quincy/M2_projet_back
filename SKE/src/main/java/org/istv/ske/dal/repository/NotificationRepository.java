package org.istv.ske.dal.repository;

import org.istv.ske.dal.Notification;
import org.istv.ske.dal.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Quentin on 06/06/2017.
 */

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {

    Notification findNotificationById(int id);

    List<Notification> findNotificationByUser(User user);
}
