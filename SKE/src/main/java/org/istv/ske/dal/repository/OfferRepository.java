package org.istv.ske.dal.repository;

import java.util.List;

import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

	List<Offer> findByUserAndStatus(User user, boolean status);

	List<Offer> findByStatusOrderByIdDesc(Boolean status);
}
