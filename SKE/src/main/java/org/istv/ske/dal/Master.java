package org.istv.ske.dal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "master")
public class Master {
	
	@Id
	private int masterId;
	private boolean skillValidation;
	public int getMasterId() {
		return masterId;
	}
	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}
	public boolean isSkillValidation() {
		return skillValidation;
	}
	public void setSkillValidation(boolean skillValidation) {
		this.skillValidation = skillValidation;
	}
	public Master(int masterId, boolean skillValidation) {
		super();
		this.masterId = masterId;
		this.skillValidation = skillValidation;
	}
	public Master() {
		super();
	}
	
}
