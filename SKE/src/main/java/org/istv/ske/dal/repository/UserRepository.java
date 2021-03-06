package org.istv.ske.dal.repository;

import java.util.List;

import org.istv.ske.dal.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUserMail(String userMail);
	
	List<User> findByToken(String token);
}
