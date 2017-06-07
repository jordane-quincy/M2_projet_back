package org.istv.ske.core.exception;

public class BadRequestException extends Exception {

	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}
	
	public int getStatus() {
		return 400;
	}
	
}
