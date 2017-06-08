package org.istv.ske.messages.manager;

import java.util.Date;
import java.util.List;

import org.istv.ske.core.service.NotificationService;
import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.Notification;
import org.istv.ske.dal.entities.Remark;
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
     */
    public void createSimpleNotification(User user) {
        System.out.println("Création d'une notif");

        String title = "";
        String content = "";

        System.out.println("Simple notif");
        notificationService.createNotification(title, content, TypeNotification.SIMPLE.toString(), user);
    }

    public void createMeetingNotification(Appointment appointment, User destinataire, User expediteur) {
        System.out.println("Creation d'une notif et envoie d'un mail");
        System.out.println("Prise de rdv notif");

        String title = "Confirmation de prise de rendez-vous";
        String content = "Un rendez-vous a été créé à la date suivante : " + appointment.getDate();

        notificationService.createNotification(title, content, TypeNotification.MEETING.toString(), destinataire);

        Email emailMeeting = new Email(EmailType.NOTIFICATION_EMAIL);
        emailMeeting.setDestinataire(destinataire);
        emailMeeting.setExpediteur(expediteur);

        emailMeeting.init();

        emailMeeting.setObjet(title);
        emailMeeting.setContenuMail(content);

        emailClient.sendEmail(emailMeeting);
    }

    public void createRemarkNotification(Remark remark, User destinataire, User expediteur) {
        System.out.println("Creation d'une notif et envoie d'un mail");
        System.out.println("Notif pour signaler une note");

        String title = "Vous avez reçu une note ";
        String content = remark.getOffer().getTitle() + " a été noté " + remark.getGrade() + "/5" + " Commentaire : " + remark.getText();

        notificationService.createNotification(title, content, TypeNotification.REMARK.toString(), destinataire);

        Email emailMeeting = new Email(EmailType.NOTIFICATION_EMAIL);
        emailMeeting.setDestinataire(destinataire);
        emailMeeting.setExpediteur(expediteur);

        emailMeeting.init();

        emailMeeting.setObjet(title);
        emailMeeting.setContenuMail(content);

        emailClient.sendEmail(emailMeeting);
    }

    public void deleteNotification(Notification notification) {
        notificationService.deleteNotification(notification);
    }

    public List<Notification> getUserNotifications(User user) {
        return notificationService.findNotificationByUser(user);
    }
}
