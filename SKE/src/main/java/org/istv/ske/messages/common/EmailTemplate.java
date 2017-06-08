package org.istv.ske.messages.common;

import org.istv.ske.messages.enums.EmailType;
import org.istv.ske.messages.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by abdel on 07/06/2017.
 */
@Service
public class EmailTemplate {
    private TemplateEngine templateEngine;

    @Autowired
    public EmailTemplate(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(Email email) {
        Context context = new Context();
        context.setVariable("message", email.getContenuMail());
        context.setVariable("objet", email.getObjet());
        context.setVariable("urlActivationAccount", email.getUrlActivationAccount());

        if(email.getEmailType().equals(EmailType.ACTIVATION_EMAIL)){
            context.setVariable("prenomDest", email.getDestinataire().getUserFirstName());
            context.setVariable("nomDest", email.getDestinataire().getUserName());
            return templateEngine.process("activateAccount", context);
        } else {
            context.setVariable("prenomDest", email.getDestinataire().getUserFirstName());
            context.setVariable("nomDest", email.getDestinataire().getUserName());
            context.setVariable("prenomExp", email.getExpediteur().getUserFirstName());
            context.setVariable("nomExp", email.getExpediteur().getUserName());
            return templateEngine.process("notificationEmail", context);
        }
    }
}
