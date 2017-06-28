package org.istv.ske.core.exception;

// Classe qui définit l'exeption à retourner au client lorsqu'une erreur est survenu côté Back
public class InternalException extends Exception {
	
	private static final long serialVersionUID = 1L;

	// Message a afficher au client
	public InternalException(String message) {
		super(message);
	}
	
	// Code erreur 500 lorsque l'erreur vient du Back
	public int getStatus() {
		return 500;
	}
}
