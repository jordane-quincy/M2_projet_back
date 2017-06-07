package org.istv.ske.dal.service;

import java.util.List;

import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.DomainRepository;
import org.istv.ske.dal.repository.OfferRepository;
import org.istv.ske.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	OfferRepository offerRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	DomainRepository domainRepository;

	@Override
	public Offer createOffer(User user, String titleOffer, int duration, String descriptionOffer, Long domainId) {
		Offer offer = new Offer();

		offer.setAppointments(null);
		offer.setDescription(descriptionOffer);
		offer.setDuration(duration);
		offer.setRemarks(null);
		offer.setDomain(domainRepository.findOne(domainId));
		offer.setTitle(titleOffer);
		offer.setUser(user);

		offerRepository.save(offer);
		return offer;
	}

	@Override
	public void deleteOffer(long idOffer) {
		offerRepository.delete(idOffer);
	}

	@Override
	public Offer updateOffer(Offer offer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> getAll(long idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offer getOffer(long Offer) {
		return offerRepository.findOne(Offer);
	}

}
