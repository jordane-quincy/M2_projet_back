package org.istv.ske.messages.manager;

import org.istv.ske.SkeApplication;
import org.istv.ske.core.service.AppointmentService;
import org.istv.ske.core.service.OfferService;
import org.istv.ske.core.service.RemarkService;
import org.istv.ske.core.service.UserService;
import org.istv.ske.dal.entities.*;
import org.istv.ske.messages.common.EmailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Quentin on 08/06/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SkeApplication.class})
public class NotificationManagerTest {

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

    @Test
    public void createSimpleNotification() throws Exception {
        User user1 = userService.getUser(1L);
        Notification notifInsert = notificationManager.createSimpleNotification(user1, "titre", "contenu");

        Notification notifInDB = notificationManager.getNotificationById(notifInsert.getId());

        assertEquals(notifInsert.getId(), notifInDB.getId());
    }

    @Test
    public void createMeetingNotification() throws Exception {
        User user1 = userService.getUser(1L);
        User user2 = userService.getUser(2L);

        Offer offer1 = offerService.findById(1L);

        Appointment appointment1 = appointmentService.createAppointment(offer1, user2, new Date(1221436800));

        Notification notifInsert = notificationManager.createMeetingNotification(appointment1, user1, user2);

        Notification notifInDB = notificationManager.getNotificationById(notifInsert.getId());

        assertEquals(notifInsert.getId(), notifInDB.getId());
    }

    @Test
    public void createRemarkNotification() throws Exception {
        User user1 = userService.getUser(1L);
        User user2 = userService.getUser(2L);

        Offer offer1 = offerService.findById(1L);
        Remark remark1 = remarkService.createRemark("bon cours", 4, offer1);
        Appointment appointment1 = appointmentService.createAppointment(offer1, user2, new Date(1221436800));

        Notification notifInsert = notificationManager.createRemarkNotification(remark1, user1, user2);

        Notification notifInDB = notificationManager.getNotificationById(notifInsert.getId());

        assertEquals(notifInsert.getId(), notifInDB.getId());
    }

}