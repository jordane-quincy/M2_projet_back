package org.istv.ske.dal.service;

import java.util.Collection;
import java.util.List;

import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.Remark;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.OfferRepository;
import org.istv.ske.dal.repository.RemarkRepository;
import org.istv.ske.dal.repository.SubjectRepository;
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
	SubjectRepository subjectRepository;
	
	@Autowired 
	RemarkRepository remarkRepository;

	@Override
	public Offer createOffer(User user, String titleOffer, int duration, String descriptionOffer, Long subjectId) {
		Offer offer = new Offer();

		offer.setAppointments(null);
		offer.setDescription(descriptionOffer);
		offer.setDuration(duration);
		offer.setRemarks(null);
		offer.setSubject(subjectRepository.findOne(subjectId));
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
	public List<Offer> getAll(long idUser) {
		return offerRepository.findByUser(userRepository.findOne(idUser));
	}

	@Override
	public Offer getOffer(long Offer) {
		return offerRepository.findOne(Offer);
	}

	@Override
	public Offer updateOffer(long offerID, String offerTitle, int duration, String offerDescription, long subectID) {
		Offer offer = offerRepository.findOne(offerID);
		offer.setTitle(offerTitle);
		offer.setDuration(duration);
		offer.setDescription(offerDescription);
		offer.setSubject(subjectRepository.findOne(subectID));
		offerRepository.save(offer);
		return offer;
	}

	@Override
	public Offer addCommentary(long offerID, String comment) {
		Offer offer = offerRepository.findOne(offerID);
		Collection<Remark> remList =  offer.getRemarks();
		Remark rem = new Remark(comment, offer);
		remList.add(rem);
		offer.setRemarks(remList);
		remarkRepository.save(rem);
		offerRepository.save(offer);		
		
		return offer;
	}

}
