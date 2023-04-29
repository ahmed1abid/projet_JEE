package org.projetjee.sessions;

public class TimeOverlapException extends Exception{
	
	private String message;
	
	public TimeOverlapException(String message) {
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
