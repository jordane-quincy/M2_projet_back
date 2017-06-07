package org.istv.ske.core.service;

import org.istv.ske.dal.entities.User;


public interface AccountCertificationService {
	public User activate(String token);
	public boolean isActivated(long userID);
}
