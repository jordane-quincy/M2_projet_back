package org.istv.ske.core.exception;

// Classe qui définit l'exeption à retourner au client lorsqu'une erreur est survenu côté Front
public class BadRequestException extends Exception {

	private static final long serialVersionUID = 1L;

	// Message a afficher au client
	public BadRequestException(String message) {
		super(message);
	}
	
	// Code erreur 500 lorsque l'erreur vient du Front
	public int getStatus() {
		return 400;
	}
	
}
