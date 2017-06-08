package org.istv.ske.core.service;

import java.util.ArrayList;
import java.util.List;

import org.istv.ske.dal.entities.Domain;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.Remark;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.OfferRepository;
import org.istv.ske.dal.repository.RemarkRepository;
import org.istv.ske.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RemarkRepository remarkRepository;

	@Override
	public Offer createOffer(User user, String title, int duration, String description, Domain domain) {
		Offer offer = new Offer();
		offer.setDescription(description);
		offer.setDuration(duration);
		offer.setDomain(domain);
		offer.setTitle(title);
		offer.setUser(user);
		offerRepository.save(offer);
		return offer;
	}

	@Override
	public void deleteOffer(Long offerId) {
		offerRepository.delete(offerId);
	}

	@Override
	public List<Offer> findByUserId(Long userId) {
		User user = userRepository.findOne(userId);
		return offerRepository.findByUser(user);
	}

	@Override
	public Offer findById(Long id) {
		return offerRepository.findOne(id);
	}

	@Override
	public Offer updateOffer(Long offerId, String title, int duration, String description, Domain domain) {
		Offer offer = offerRepository.findOne(offerId);
		offer.setTitle(title);
		offer.setDuration(duration);
		offer.setDescription(description);
		offer.setDomain(domain);
		offerRepository.save(offer);
		return offer;
	}

	@Override
	public Offer addComment(Long offerId, String comment, int grade) {
		Offer offer = offerRepository.findOne(offerId);
		Remark remark = new Remark(comment, offer, grade);
		offer.getRemarks().add(remark);
		remarkRepository.save(remark);
		offerRepository.save(offer);

		return offer;
	}
	
	@Override
	public List<Offer> findAll() {
		List<Offer> offer = new ArrayList<>();
		for (Offer d : offerRepository.findAll()) {
			offer.add(d);
		}
		return offer;
	}

}
