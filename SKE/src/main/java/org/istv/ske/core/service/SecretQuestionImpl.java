package org.istv.ske.core.service;

import java.util.ArrayList;
import java.util.List;

import org.istv.ske.dal.entities.Appointment;
import org.istv.ske.dal.entities.SecretQuestion;
import org.istv.ske.dal.entities.Skill;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.SecretQuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretQuestionImpl implements SecretQuestionService{
	@Autowired
	private SecretQuestionRepository secretQuestionRepository ;
	@Override
	public SecretQuestion createSecretQuestion(User user, String question, String answer) {
		SecretQuestion secretQuestion = new SecretQuestion();
		secretQuestion.setQuestion(question);
		secretQuestion.setAnswer(answer);
		secretQuestionRepository.save(secretQuestion);
		return secretQuestion;
	}

	@Override
	public SecretQuestion updateSecretQuestion(User user, String question, String answer) {
		 SecretQuestion secretQuestion = secretQuestionRepository.findOne(user.getId());
		  secretQuestion.setUser(user);
		  secretQuestion.setQuestion(question);
		  secretQuestion.setAnswer(answer);
		  secretQuestionRepository.save(secretQuestion);
		return secretQuestion;
	}

	@Override
	public void deleteSecretQuestion(SecretQuestion secretQuestion) {
		secretQuestionRepository.delete(secretQuestion);
		
	}

	@Override
	public List<SecretQuestion> getAll() {
		List<SecretQuestion> secretQuestions = new ArrayList<>();
		for(SecretQuestion sq: secretQuestionRepository.findAll()) {
			secretQuestions.add(sq);
		}
		return secretQuestions;
	}

}
