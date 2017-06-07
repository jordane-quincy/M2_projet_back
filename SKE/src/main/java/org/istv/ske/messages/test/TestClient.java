package org.istv.ske.messages.test;

import org.istv.ske.messages.common.EmailClient;
import org.istv.ske.messages.common.EmailType;
import org.istv.ske.messages.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdel on 06/06/2017.
 */
@RestController
@RequestMapping("/TestClient")
public class TestClient {

    @Autowired
    private EmailClient emailClient;

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public void testEmailClient() {
        System.out.println("Debut envoi mail");
        Email email = new Email(EmailType.ACTIVATION_EMAIL);
        emailClient.sendEmail(email);
        System.out.println("Fin envoi mail");
    }
}
