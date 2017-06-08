package org.istv.ske.core.service;

import java.util.ArrayList;
import java.util.List;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.Remark;
import org.istv.ske.dal.repository.RemarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemarkServiceImpl implements RemarkService {

	
	@Autowired
	private RemarkRepository remarkRepository;
	@Override
	public Remark createRemark(String text, int grade, Offer offer) {
		Remark remark = new Remark();
		remark.setText(text);
		remark.setGrade(grade);
		remark.setOffer(offer);
		remarkRepository.save(remark);
		return remark;
	}

	@Override
	public Remark updateRemark(Remark remark, String text, int grade, Offer offer) {
		Remark remarkFound =  remarkRepository.findOne(remark.getId());
		remarkFound.setText(text);;
		remarkFound.setOffer(offer);
		remarkFound.setGrade(grade);
		remarkRepository.save(remarkFound);
		return remarkFound;
	}

	@Override
	public void deleteRemark(Remark remark) {
		 remarkRepository.delete(remark);
		
	}

	@Override
	public List<Remark> getAll() {
		List<Remark> remarks = new ArrayList<>();
		for(Remark r : remarkRepository.findAll()) {
			remarks.add(r);
		}
		return remarks;
	}

}
