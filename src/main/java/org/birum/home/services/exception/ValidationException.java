package org.birum.home.services.exception;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -7219653418893853059L;

	public ValidationException(String message) {
		super(message);
	}
}
