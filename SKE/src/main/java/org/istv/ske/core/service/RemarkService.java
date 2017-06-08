package org.istv.ske.core.service;

import java.util.List;

import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.Remark;

public interface RemarkService {

	public Remark createRemark(String text, int Grade, Offer offer );
	public Remark updateRemark(Remark remark, String text, int Grade, Offer offer);
	public void  deleteRemark(Remark remark);
	public List<Remark> getAll();
}
