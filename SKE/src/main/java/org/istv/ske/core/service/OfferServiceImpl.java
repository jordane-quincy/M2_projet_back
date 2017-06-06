package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.Offer;
import org.istv.ske.dal.User;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

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

}
