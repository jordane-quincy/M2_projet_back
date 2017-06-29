package org.istv.ske.dal.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * La table notification permet d'envoyer des notifications aux utilisateurs
 * pour différentes raisons telles que pour des création de rendez-vous, des
 * rappels, ...
 * 
 * @param id
 *            Identifiant d'une notification.
 * @param title
 *            Titre du message de notification.
 * @param content
 *            Contenu du message.
 * @param type
 *            Type de la notification choisi parmi les valeurs de l'énumeration
 *            'NotificationType' (simple, confimer, rediriger, rendez-vous,
 *            avis)
 * @param creationDate
 *            Date de création de la notification.
 * @param status
 *            Status du message actuel choisi parmi les valeurs de l'énumération
 *            'NotificationStatus' (lue, non lue) et qui permet de savoir si un
 *            message a été lu ou non.
 * @param users
 *            L'utilisateur auquel est lié la notification.
 */
@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	private String content;

	@Enumerated(EnumType.STRING)
	private NotificationType type;

	public enum NotificationType {
		SIMPLE, CONFIRM, REDIRECT, MEETING, REMARK
	}

	private Date creationDate;

	@Enumerated(EnumType.STRING)
	private NotificationStatus status;

	public enum NotificationStatus {
		READ, NOTREAD
	}

	@ManyToOne
	@JsonIgnore
	private User user;

	/**
	 * Constucteur utilisé dans la partie core pour créér une notification,
	 * aucun parametre en entrée n'est requis, la date étant celle de création,
	 * une nouvelle date y est instanciée.
	 */
	public Notification() {
		creationDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}
}
