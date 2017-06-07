package org.istv.ske.messages.model;

/**
 * Created by abdel on 06/06/2017.
 */
public class Email {
    private String raison;
    private String client;
    private String civilite;
    private String prenom;
    private String nom;
    private String email;
    private String telephone;
    private String dateNaissance;
    private String message;

    public Email() {
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHtmlMsg() {
        return "<strong> <p>Raison de la demande : " + raison + "</p>"
                + "<p>Nom du contact : " + civilite + " " + prenom + " " + nom
                + "</p>" + "<p>Email du client : " + email + "</p>" + "<p>Téléphone : "
                + telephone + "</p>" + "<p>Date de naissance : " + dateNaissance + "</p></strong>"
                + "<pre>" + message + "</pre>";
    }

    @Override
    public String toString() {
        return "Email [raison=" + raison + ", client=" + client + ", civilite="
                + civilite + ", prenom=" + prenom + ", nom=" + nom + ", email="
                + email + ", telephone=" + telephone + ", dateNaissance="
                + dateNaissance + ", message=" + message + "]";
    }
}
