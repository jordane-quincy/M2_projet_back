package org.istv.ske.messages.test;

import org.istv.ske.core.service.AppointmentService;
import org.istv.ske.core.service.OfferService;
import org.istv.ske.core.service.RemarkService;
import org.istv.ske.core.service.UserService;
import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.Remark;
import org.istv.ske.dal.entities.User;
import org.istv.ske.messages.common.EmailClient;
import org.istv.ske.messages.enums.EmailType;
import org.istv.ske.messages.enums.TypeNotification;
import org.istv.ske.messages.manager.NotificationManager;
import org.istv.ske.messages.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

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

    @Autowired
    private OfferService offerService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private RemarkService remarkService;

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

        User user1 = userService.getUser(1L);
        User user2 = userService.getUser(2L);

        Offer offer1 = offerService.findById(1L);

        Appointment appointment1 = appointmentService.createAppointment(offer1, user2, new Date(1221436800));
        Remark remark1 = remarkService.createRemark("bon cours", 4, offer1);
       // notificationManager.createSimpleNotification(user1);
       // notificationManager.createMeetingNotification(appointment1, user1, user2);
        notificationManager.createRemarkNotification(remark1, user1, user2);
        System.out.println("Fin creation notif");
    }
}
