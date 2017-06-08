package org.istv.ske.messages.manager;

import java.util.Date;

import org.istv.ske.core.service.NotificationService;
import org.istv.ske.dal.entities.Notification;
import org.istv.ske.dal.entities.User;
import org.istv.ske.messages.common.EmailClient;
import org.istv.ske.messages.enums.EmailType;
import org.istv.ske.messages.enums.TypeNotification;
import org.istv.ske.messages.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by abdel on 06/06/2017.
 */

@Component
public class NotificationManager {

    @Autowired
    NotificationService notificationService;

    @Autowired
    private EmailClient emailClient;

    public NotificationManager() {
    }

    /**
     * Fonction qui creer une notif et qui gere le contenu en fonction du type
     *
     * @param user
     * @param typeNotification
     */
    public void createSimpleNotification(User user, TypeNotification typeNotification) {
        System.out.println("Création d'une notif");

        String title;
        String content;

        switch (typeNotification) {
            case SIMPLE:
                System.out.println("Simple notif");
                title = "test title";
                content = "test content";
                notificationService.createNotification(title, content, typeNotification.toString(), user);
                break;
            case REDIRECT:
                System.out.println("Redirection notif");
                title = "test title";
                content = "test content";
                notificationService.createNotification(title, content, typeNotification.toString(), user);
                break;
            default:
                break;
        }
    }

    public void createNotificationWithEmail(User destinataire, User expediteur, TypeNotification typeNotification) {
        System.out.println("Création d'une notif et envoie d'un mail");

        String title;
        String content;

        switch (typeNotification) {

            case MEETING:
                System.out.println("Prise de rdv notif");
                title = "test title";
                content = "test content";
                notificationService.createNotification(title, content, typeNotification.toString(), destinataire);

                // TODO : Envoyer un mail
                Email emailMeeting = new Email(EmailType.NOTIFICATION_EMAIL);
                emailMeeting.setDestinataire(destinataire);
                emailMeeting.setExpediteur(expediteur);
                emailMeeting.setObjet(title);
                emailMeeting.setContenuMail(content);

                emailClient.sendEmail(emailMeeting);
                break;

            case REMARK:
                System.out.println("Notif notation");
                title = "test title";
                content = "test content";
                notificationService.createNotification(title, content, typeNotification.toString(), destinataire);

                // TODO : Envoyer un mail
                Email emailRemark = new Email(EmailType.NOTIFICATION_EMAIL);
                emailRemark.setDestinataire(destinataire);
                emailRemark.setExpediteur(expediteur);
                emailRemark.setObjet(title);
                emailRemark.setContenuMail(content);

                emailClient.sendEmail(emailRemark);
                break;

            default:
                break;
        }
    }

    /**
     * Function qui contacte le DAO pour supprimer une notif en BDD
     */
    public void deleteNotification(Notification notification) {
        notificationService.deleteNotification(notification);
    }


}
