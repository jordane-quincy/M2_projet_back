package org.istv.ske.dal.repository;

import org.istv.ske.dal.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	User findByUserMail(String mail);
}
