package org.istv.ske.messages.common;

import org.istv.ske.messages.enums.EmailType;
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
            messageHelper.setSubject(emailModel.getObjet());

            if (emailModel.getDestinataire() != null) {
                messageHelper.setTo(emailModel.getDestinataire().getUserMail());
                if (emailModel.getEmailType().equals(EmailType.NOTIFICATION_EMAIL)) {
                    if (emailModel.getExpediteur() != null) {
                        messageHelper.setReplyTo(emailModel.getExpediteur().getUserMail());
                    } else {
                        throw new NullPointerException("Dans le cadre d'une notification l'expediteur doit etre présent !!!!");
                    }
                }
            } else {
                throw new NullPointerException("Le destinataire doit impérativement etre présent !!!!");
            }
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
