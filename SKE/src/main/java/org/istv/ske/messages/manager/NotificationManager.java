package org.istv.ske.messages.manager;

import java.util.Date;

import org.istv.ske.core.service.NotificationService;
import org.istv.ske.dal.entities.User;
import org.istv.ske.messages.enums.TypeNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by abdel on 06/06/2017.
 */

@Component
public class NotificationManager {

    @Autowired
    NotificationService notificationService;

    public NotificationManager() {
    }

    /**
     * Fonction qui creer une notif et qui gere le contenu en fonction du type
     *
     * @param user
     * @param typeNotification
     */
    public void createNotification(User user, TypeNotification typeNotification) {
        System.out.println("Création d'une notif");

        switch (typeNotification) {
            case SIMPLE:
                System.out.println("Simple notif");
                notificationService.createNotification("", "", typeNotification.toString(), new Date(), user);
                break;

            case CONFIRM:
                System.out.println("Confirmation notif");
                break;

            case REDIRECT:
                System.out.println("Redirection notif");
                break;

            case MEETING:
                System.out.println("Prise de rdv notif");
                break;

            default:
                break;
        }
    }

    /**
     * Function qui contacte le DAO pour supprimer une notif en BDD
     */
    public void deleteNotification() {

    }


}
