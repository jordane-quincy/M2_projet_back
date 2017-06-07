package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.Offer;
import org.istv.ske.dal.User;
import org.istv.ske.dal.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	OfferRepository offerRepository;
	
	@Override
	public Offer createOffer(User idUser, String titleOffer, int duration, String descriptionOffer) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public void deleteOffer(int idOffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Offer updateOffer(Offer offer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> getAll(int idUser) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Offer getOffer(int Offer) {
		// TODO Auto-generated method stub
		return null;
	}

}
