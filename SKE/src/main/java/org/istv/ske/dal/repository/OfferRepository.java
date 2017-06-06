package org.istv.ske.dal.repository;

import org.istv.ske.dal.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Integer>{

}