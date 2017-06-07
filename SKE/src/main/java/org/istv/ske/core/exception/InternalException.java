package org.istv.ske.core.exception;

public class InternalException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InternalException(String message) {
		super(message);
	}
	
	public int getStatus() {
		return 500;
	}
}
