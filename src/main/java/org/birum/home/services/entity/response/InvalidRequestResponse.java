package org.birum.home.services.entity.response;

import java.io.Serializable;

public class InvalidRequestResponse implements Serializable {

	private static final long serialVersionUID = -70398788362847785L;

	private String error;
	
	private String context;
	
	public InvalidRequestResponse(final String error) {
		this.error = error;
	}
	
	public InvalidRequestResponse(final String error, final String context) {
		this.error = error;
		this.context = context;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	
}
