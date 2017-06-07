package org.istv.ske.messages.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * Created by abdel on 06/06/2017.
 */
@Service
public class EmailClient {
    private JavaMailSender mailSender;

    @Autowired
    public EmailClient(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void prepareAndSend(String recipient, String message) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("ske.noreply@gmail.com");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Sample mail subject");
            messageHelper.setText(message);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
