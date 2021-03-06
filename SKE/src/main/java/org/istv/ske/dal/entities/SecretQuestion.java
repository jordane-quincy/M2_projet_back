package org.istv.ske.dal.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * La table secretQuestion permet d'ajouter une sécurité suplémentaire sur le
 * compte utilisateur pour par exemple réinitialiser son mot de passe.
 * 
 * @param id
 *            Identifiant de la question.
 * @param question
 *            Qestion secrete de l'utilisateur.
 * @param answer
 *            Réponse secrete de l'utilisateur.
 * @param user
 *            Utilisateur à qui appartient cette question.
 */
@Entity
public class SecretQuestion {

	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;

	private String question;

	@JsonIgnore
	private String answer;

	@OneToOne(mappedBy = "question")
	@JsonIgnore
	private User user;

	public SecretQuestion() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constucteur utilisé dans la partie core pour créér une question secrete
	 * avec sa réponse.
	 * 
	 * @param question
	 * @param answer
	 */
	public SecretQuestion(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
