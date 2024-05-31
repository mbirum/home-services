package org.birum.home.services.entity.response;

import java.io.Serializable;

public class StringResponse implements Serializable {

	private static final long serialVersionUID = -8493367254546947065L;
	
	private String data;
	
	public StringResponse(final String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
