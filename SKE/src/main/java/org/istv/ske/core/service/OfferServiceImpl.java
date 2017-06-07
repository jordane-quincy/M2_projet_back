package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.Offer;
import org.istv.ske.dal.User;
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
	
	@Override
	public Offer createOffer(long idUser, String titleOffer, int duration, String descriptionOffer) {
		Offer offer = new Offer();
		
		//offer.setAppointments(appointments);
		offer.setDescription(descriptionOffer);
		offer.setDuration(duration);
		//offer.setRemarks(remarks);
		//offer.setSubject(subject);
		offer.setTitle(titleOffer);
		offer.setUser(userRepository.findOne(idUser));
		
		offerRepository.save(offer);
		// TODO Auto-generated method stub
		return offer;
	}

	
	@Override
	public void deleteOffer(long idOffer) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

}
