package org.istv.ske.messages.common;

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
        context.setVariable("prenom", email.getDestinataire().getUserFirstName());
        context.setVariable("nom", email.getDestinataire().getUserName());
        return templateEngine.process("activateAccount", context);
    }
}
