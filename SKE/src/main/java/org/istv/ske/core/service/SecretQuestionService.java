package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.entities.SecretQuestion;
import org.istv.ske.dal.entities.User;

public interface SecretQuestionService {
	public SecretQuestion createSecretQuestion(User user, String question, String answer);
	public SecretQuestion updateSecretQuestion(User user, String question, String answer);
	public void deleteSecretQuestion(SecretQuestion secretQuestion);
	public List<SecretQuestion> getAll();
}
