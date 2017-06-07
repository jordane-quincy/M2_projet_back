package org.istv.ske.dal.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class SecretQuestion {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String question;
	
	private String answer;
	
	@OneToOne(mappedBy="question")
	@JsonIgnore
	private User user;
	
	public SecretQuestion() {
		// TODO Auto-generated constructor stub
	}

	public SecretQuestion(String question, String answer, User user) {
		super();
		this.question = question;
		this.answer = answer;
		this.user = user;
	}

	public SecretQuestion(String question, String answer) {
		super();
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
