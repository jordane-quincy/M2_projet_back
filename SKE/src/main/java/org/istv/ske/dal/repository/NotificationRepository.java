package org.istv.ske.dal.repository;

import org.istv.ske.dal.entities.Notification;
import org.istv.ske.dal.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Quentin on 06/06/2017.
 */

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

    Notification findNotificationById(Long id);

    List<Notification> findNotificationByUser(User user);
}
