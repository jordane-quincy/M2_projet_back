package org.istv.ske.messages.common;

import org.istv.ske.messages.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${mailSender.username}")
    private String username;

    @Autowired
    private EmailTemplate emailTemplate;

    @Autowired
    public EmailClient(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(Email emailModel) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(username);
            messageHelper.setReplyTo(emailModel.getExpediteur().getUserMail());
            messageHelper.setTo(emailModel.getDestinataire().getUserMail());
            messageHelper.setSubject(emailModel.getObjet());

            String content = emailTemplate.build(emailModel);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
