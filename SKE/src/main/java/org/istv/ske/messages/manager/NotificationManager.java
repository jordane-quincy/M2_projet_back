package org.istv.ske.messages.manager;

import org.istv.ske.messages.dao.NotificationDAO;
import org.istv.ske.messages.enums.TypeNotification;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by abdel on 06/06/2017.
 */

@Component
public class NotificationManager {

    private NotificationDAO notificationDAO;

    public NotificationManager() {
    }

    /**
     * Fonction qui creer une notif et qui gere le contenu en fonction du type
     * @param userId
     * @param typeNotification
     */
    public void createNotification(int userId, TypeNotification typeNotification) {
        System.out.println("Création d'une notif");
        Timestamp date = new Timestamp(System.currentTimeMillis());

        switch (typeNotification) {
            case SIMPLE:
                System.out.println("Simple notif");

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
    public void deleteNotification(){

    }


}
