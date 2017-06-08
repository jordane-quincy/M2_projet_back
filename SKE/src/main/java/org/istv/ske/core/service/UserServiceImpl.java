package org.istv.ske.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.istv.ske.dal.entities.Formation;
import org.istv.ske.dal.entities.SecretQuestion;
import org.istv.ske.dal.entities.Skill;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.entities.User.Role;
import org.istv.ske.dal.repository.SecretQuestionRepository;
import org.istv.ske.dal.repository.SkillRepository;
import org.istv.ske.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SkillRepository skillRepository;

	@Autowired
	private SecretQuestionRepository secretQuestionRepository;

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		for (User u : userRepository.findAll()) {
			users.add(u);
		}
		return users;
	}

	@Override
	public User getUser(Long userID) {
		return userRepository.findOne(userID);
	}

	@Override
	public User createUser(String email, String name, String firstName, String password, Long birthday,
			Formation formation, SecretQuestion secretQuestion, List<String> skills) {
		Role role = (email.endsWith("@etu.univ-valenciennes.fr") ? Role.STUDENT : Role.TEACHER);
		User user = new User();
		user.setBirthday(new Date(birthday));
		user.setCredit(5);
		user.setFormation(formation);
		for (String skill : skills) {
			Skill sk = skillRepository.findByLabel(skill);
			if (sk == null) {
				sk = new Skill(skill);
				skillRepository.save(sk);
			}
			user.getSkills().put(sk, false);
		}
		SecretQuestion savedSecretQuestion = secretQuestionRepository.save(secretQuestion);
		user.setQuestion(savedSecretQuestion);
		user.setRole(role);
		user.setUserFirstName(firstName);
		user.setUserMail(email);
		user.setUserName(name);
		user.setUserPassword(password);
		return userRepository.save(user);
	}

	@Override
	public List<User> getUserByToken(String token) {
		return userRepository.findByToken(token);
	}

	@Override
	public boolean emailAlreadyExists(String email) {
		User user = userRepository.findByUserMail(email);
		return user != null;
	}

	private boolean wasSkillValidated(Skill skill, Map<Skill, Boolean> skills) {
		for(Entry<Skill, Boolean> entry : skills.entrySet()) {
			if(entry.getKey().getLabel().equals(skill.getLabel())) {
				return entry.getValue();
			}
		}
		return false;
	}
	
	@Override
	public User getUserByUserMail(String email) {
		User user = userRepository.findByUserMail(email);
		return user;
	}

	public User updateUser(Long id, String password, Formation formation, List<String> skills) {
		User user = userRepository.findOne(id);
		user.setUserPassword(password);
		user.setFormation(formation);
		Map<Skill, Boolean> oldSkills = user.getSkills();
		Map<Skill, Boolean> newSkills = new HashMap<>();
		for(String skill : skills) {
			Skill sk = skillRepository.findByLabel(skill);
			if(sk == null) {
				sk = new Skill(skill);
				skillRepository.save(sk);
			}
			newSkills.put(sk, wasSkillValidated(sk, oldSkills));
		}
		user.setSkills(newSkills);
		userRepository.save(user);
		return user;
	}

	@Override
	public void setPassword(String email, String password) {
		User user = userRepository.findByUserMail(email);
		user.setUserPassword(password);
		userRepository.save(user);
	}

	@Override
	public void setToken(User user, String token) {
		user.setToken(token);
		userRepository.save(user);		
	}}
