package org.istv.ske.messages.model;

import org.istv.ske.dal.User;
import org.istv.ske.messages.common.EmailType;

/**
 * Created by abdel on 06/06/2017.
 */
public class Email {
    private User destinataire;
    private User expediteur;
    private String contenuMail;
    private String objet;
    private String emailType;
    private String urlActivationAccount;

    public Email(String emailType) {
        contenuMail = "Le contenu de mon mail ....";
        objet = "Objet de mon mail";

        User dest = new User();
        dest.setUserMail("abdeloihid.elmardi@etu.univ-valenciennes.fr");
        dest.setUserName("el mardi");
        dest.setUserFirstName("Abdeloihid");

        User exp = new User();
        exp.setUserMail("quentin.senecat@etu.univ-valenciennes.fr");
        exp.setUserName("Senecat");
        exp.setUserFirstName("Quentin");

        destinataire = dest;
        expediteur = exp;

        if(emailType.equals(EmailType.ACTIVATION_EMAIL)){
            urlActivationAccount= "http://google.fr";
        }
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getUrlActivationAccount() {
        return urlActivationAccount;
    }

    public void setUrlActivationAccount(String urlActivationAccount) {
        this.urlActivationAccount = urlActivationAccount;
    }

    public User getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(User destinataire) {
        this.destinataire = destinataire;
    }

    public User getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(User expediteur) {
        this.expediteur = expediteur;
    }

    public String getContenuMail() {
        return contenuMail;
    }

    public void setContenuMail(String contenuMail) {
        this.contenuMail = contenuMail;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }
}
