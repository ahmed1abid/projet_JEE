package org.projetjee.sites;

public class SiteAlreadyExistsException extends Exception {
	
	private String message;
	
	public SiteAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}
	
	public String toString() {
		return "SiteAlreadyExistsException: " + this.message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
