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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int credit;
	
	@Column(unique = true)
	private String userMail;
	
	private String userPassword;
	
	private String userName;
	
	private String userFirstName;
	
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
		STUDENT,
		TEACHER
	}

	@ManyToOne
	private Formation formation;
	
	@ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "VALIDATED_SKILLS")
    @MapKeyColumn(name = "SKILL_ID")
    @Column(name = "VALIDATED")
	private Map<Skill, Boolean> skills = new HashMap<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="applicant")
	private List<Appointment> appointments;
	
	public User() {

	}

	public User(int credit, String userMail, String userPassword, String userName, String userFirstName, Date birthday,
			Collection<Offer> offers, Collection<Notification> notifications, Role role, Formation formation) {
		this.credit = credit;
		this.userMail = userMail;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userFirstName = userFirstName;
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