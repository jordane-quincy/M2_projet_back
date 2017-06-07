package org.istv.ske.messages.test;

import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.service.UserService;
import org.istv.ske.messages.common.EmailClient;
import org.istv.ske.messages.enums.EmailType;
import org.istv.ske.messages.enums.TypeNotification;
import org.istv.ske.messages.manager.NotificationManager;
import org.istv.ske.messages.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by abdel on 06/06/2017.
 */
@RestController
@RequestMapping("/TestClient")
public class TestClient {

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private NotificationManager notificationManager;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public void testEmailClient() {
        System.out.println("Debut envoi mail");
        Email email = new Email(EmailType.NOTIFICATION_EMAIL);
        email.init();
        emailClient.sendEmail(email);
        System.out.println("Fin envoi mail");
    }

    @RequestMapping(value = "/notif", method = RequestMethod.GET)
    public void testNotif() {
        System.out.println("Debut creation notif");

        User user = userService.getUser(new Long(1));

        notificationManager.createNotification(user, TypeNotification.SIMPLE);

        System.out.println("Fin creation notif");
    }
}
