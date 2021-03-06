package org.istv.ske.dal.entities;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.istv.ske.core.utils.SkillsMapJsonAdapter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * La table utilisateur permet de gérer les utilisateurs inscrits dans
 * l'application.
 * 
 * @param id
 *            Identifiant utilisateur.
 * @param credit
 *            Nombre de crédit dont dispose un utilisateur, une fois à 0 il ne
 *            peut plus participer à un cours et doit donc en donner un pour
 *            récupérer une heure.
 * @param userMail
 *            Adresse mail de l'utilisateur.
 * @param userPassword
 *            Mot de passe chiffré dans la base.
 * @param userName
 *            Nom de l'utilisateur.
 * @param userFirstName
 *            Prénom de l'utilisateur.
 * @param phoneNumber
 *            Numéro de telephone de l'utilisateur.
 * @param token
 *            Code permettant de savoir si un utilisateur est bien inscrit à
 *            l'application ou non.
 * @param birthday
 *            Date de naissance.
 * @param question
 *            Question secrete.
 * @param offers
 *            Liste des offres proposées par l'utilisateur.
 * @param notifications
 *            Liste des notifications pour cet utilisateur.
 * @param role
 *            Occupation de l'utilisateur choisi dans une énumération 'Role'
 *            (étudiant, enseignant).
 * @param formation
 *            Formation de l'utilisateur.
 * @param skills
 *            Map qui permet de savoir si une compétence a été validée par un
 *            enseignant ou non.
 * @param appointments
 *            Liste des rendez-vous pour un utilisateur.
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int credit;

	@Column(unique = true)
	private String userMail;

	@JsonIgnore
	private String userPassword;

	private String userName;

	private String userFirstName;

	private String phoneNumber;

	private String token;

	private Date birthday;

	@OneToOne
	private SecretQuestion question;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Collection<Offer> offers;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Collection<Notification> notifications;

	@Enumerated(EnumType.STRING)
	private Role role;

	public enum Role {
		STUDENT, TEACHER
	}

	@ManyToOne
	private Formation formation;

	/**
	 * Création de la table validated_skills.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "VALIDATED_SKILLS")
	@MapKeyColumn(name = "SKILL_ID")
	@Column(name = "VALIDATED")
	@JsonSerialize(using = SkillsMapJsonAdapter.class)
	private Map<Skill, Boolean> skills = new HashMap<>();

	@JsonIgnore
	@OneToMany(mappedBy = "applicant")
	private List<Appointment> appointments;

	public User() {

	}

	/**
	 * Constucteur utilisé dans la partie core pour créér un utilisateur, ici il
	 * faut beaucoup de parametres pour créer un utilisateur car pratiquement au
	 * centre de toutes les fonctionnalités.
	 * 
	 * @param credit
	 * @param userMail
	 * @param userPassword
	 * @param userName
	 * @param userFirstName
	 * @param phoneNumber
	 * @param birthday
	 * @param offers
	 * @param notifications
	 * @param role
	 * @param formation
	 */
	public User(int credit, String userMail, String userPassword, String userName, String userFirstName,
			String phoneNumber, Date birthday, Collection<Offer> offers, Collection<Notification> notifications,
			Role role, Formation formation) {
		this.credit = credit;
		this.userMail = userMail;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userFirstName = userFirstName;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.offers = offers;
		this.notifications = notifications;
		this.role = role;
		this.formation = formation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Collection<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Collection<Offer> offers) {
		this.offers = offers;
	}

	public Collection<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Collection<Notification> notifications) {
		this.notifications = notifications;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public SecretQuestion getQuestion() {
		return question;
	}

	public void setQuestion(SecretQuestion question) {
		this.question = question;
	}

	public Map<Skill, Boolean> getSkills() {
		return skills;
	}

	public void setSkills(Map<Skill, Boolean> skills) {
		this.skills = skills;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}