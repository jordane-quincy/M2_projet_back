package org.istv.ske.messages.common;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import org.istv.ske.SkeApplication;
import org.istv.ske.messages.enums.EmailType;
import org.istv.ske.messages.model.Email;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by abdel on 07/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SkeApplication.class})
public class EmailClientTest {

    @Autowired
    private JavaMailSenderImpl emailSender;

    private GreenMail smtpServer;

    @Before
    public void setUp() throws Exception {
        ServerSetup setup = new ServerSetup(65438, "localhost", "smtp");
        smtpServer = new GreenMail(setup);
        smtpServer.start();
        //On surcharge les infos afin d'avoir les memes params que GreenMail
        emailSender.setPort(65438);
        emailSender.setHost("localhost");
        emailSender.setProtocol("smtp");
    }

    @After
    public void cleanup() throws Exception {
        smtpServer.stop();
    }

    @Test
    public void sendEmail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("abd.elmardi@outlook.com");
        message.setTo("abd.elmardi@outlook.com");
        message.setSubject("subject");
        message.setText("message");
        emailSender.send(message);

        assertTrue(smtpServer.waitForIncomingEmail(5000, 1));

        Message[] messages = smtpServer.getReceivedMessages();
        assertEquals(1, messages.length);
        assertEquals("subject", messages[0].getSubject());

        String body = GreenMailUtil.getBody(messages[0]).replaceAll("=\r?\n", "");
        assertEquals("message", body);
    }
}