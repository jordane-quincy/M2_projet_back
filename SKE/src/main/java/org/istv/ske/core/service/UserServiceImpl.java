package org.istv.ske.core.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

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

	private static SecretKeySpec getKey(String secretKey) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		try {
			return new SecretKeySpec(digest.digest(new String(secretKey.getBytes(), "UTF8").getBytes()), "AES");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String chiffrer(String s) {
		String encrypted = null;
		try {
			// Instantiate the cipher
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, getKey("testduhash"));
			// Récupère la clé secrète
			byte[] cipherText = cipher.doFinal(s.getBytes("ISO-8859-1"));
			encrypted = new String(cipherText);
		} catch (Exception e) {
			System.out.println("Impossible to encrypt with AES algorithm: string=(" + s + ")");
		}
		return encrypted;
	}

	@Override
	public User createUser(String email, String name, String firstName, String password, Long birthday,
			Formation formation, SecretQuestion secretQuestion, List<String> skills, String phoneNumber) {
		Role role = (email.endsWith("@univ-valenciennes.fr") ? Role.TEACHER : Role.STUDENT);
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
		user.setUserPassword(chiffrer(password));
		user.setPhoneNumber(phoneNumber);
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
		for (Entry<Skill, Boolean> entry : skills.entrySet()) {
			if (entry.getKey().getLabel().equals(skill.getLabel())) {
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

	@Override
	public User updateUser(Long id, String lastName, String firstName, Long birthday, Formation formation,
			List<String> skills, String phoneNumber) {
		User user = userRepository.findOne(id);
		user.setFormation(formation);
		user.setUserName(lastName);
		user.setUserFirstName(firstName);

		user.setBirthday(new Date(birthday));

		Map<Skill, Boolean> oldSkills = user.getSkills();
		Map<Skill, Boolean> newSkills = new HashMap<>();
		for (String skill : skills) {
			Skill sk = skillRepository.findByLabel(skill);
			if (sk == null) {
				sk = new Skill(skill);
				skillRepository.save(sk);
			}
			newSkills.put(sk, wasSkillValidated(sk, oldSkills));
		}
		user.setSkills(newSkills);
		user.setPhoneNumber(phoneNumber);
		userRepository.save(user);
		return user;
	}

	@Override
	public void setPassword(String email, String password) {
		User user = userRepository.findByUserMail(email);
		user.setUserPassword(chiffrer(password));
		userRepository.save(user);
	}

	@Override
	public void setToken(User user, String token) {
		user.setToken(token);
		userRepository.save(user);
	}
}
