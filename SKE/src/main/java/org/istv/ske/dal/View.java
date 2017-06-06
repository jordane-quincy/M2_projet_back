package org.istv.ske.dal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;

import lombok.Data;
@Data
@Entity
public class View {
	
	@Id
	@GeneratedValue
	private int idView;
	private String textView;
	public View(int idView, String textView) {
		super();
		this.idView = idView;
		this.textView = textView;
	}
}
