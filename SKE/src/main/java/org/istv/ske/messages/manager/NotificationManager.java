package org.istv.ske.messages.manager;

import java.text.DateFormat;
import java.util.List;

import org.istv.ske.core.service.NotificationService;
import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.Notification;
import org.istv.ske.dal.entities.Remark;
import org.istv.ske.dal.entities.User;
import org.istv.ske.messages.common.EmailClient;
import org.istv.ske.messages.enums.EmailType;
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
	public Notification createSimpleNotification(User user, String title, String content) {
		System.out.println("Création d'une notif");
		System.out.println("Simple notif");
		return notificationService.createNotification(title, content, Notification.NotificationType.SIMPLE, user);
	}

	public Notification createMeetingNotification(Appointment appointment, User destinataire, User expediteur) {
		System.out.println("Creation d'une notif et envoie d'un mail");
		System.out.println("Prise de rdv notif");

		DateFormat fullDateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);

		String title = "Confirmation de prise de rendez-vous";
		String content = "Un rendez-vous a été créé à la date suivante : "
				+ fullDateFormat.format(appointment.getDate());

		Email emailMeeting = new Email(EmailType.NOTIFICATION_EMAIL);
		emailMeeting.setDestinataire(destinataire);
		emailMeeting.setExpediteur(expediteur);

		// emailMeeting.init();

		emailMeeting.setObjet(title);
		emailMeeting.setContenuMail(content);

		emailClient.sendEmail(emailMeeting);

		return notificationService.createNotification(title, content, Notification.NotificationType.MEETING,
				destinataire);
	}

	public Notification createRemarkNotification(Remark remark, User destinataire, User expediteur) {
		System.out.println("Creation d'une notif et envoie d'un mail");
		System.out.println("Notif pour signaler une note");

		String title = "Vous avez reçu une note ";
		String content = remark.getOffer().getTitle() + " a été noté " + remark.getGrade() + "/5" + " Commentaire : "
				+ remark.getText();

		Email emailMeeting = new Email(EmailType.NOTIFICATION_EMAIL);
		emailMeeting.setDestinataire(destinataire);
		emailMeeting.setExpediteur(expediteur);

		// emailMeeting.init();

		emailMeeting.setObjet(title);
		emailMeeting.setContenuMail(content);

		emailClient.sendEmail(emailMeeting);

		return notificationService.createNotification(title, content, Notification.NotificationType.REMARK,
				destinataire);
	}

	public Notification getNotificationById(Long notificationID) {
		return notificationService.findNotificationById(notificationID);
	}

	public void deleteNotification(Notification notification) {
		notificationService.deleteNotification(notification);
	}

	public List<Notification> getUserNotifications(User user) {
		return notificationService.findNotificationByUserByOrderByCreationDate(user);
	}

}