package org.projetjee.athletes;

public class NotEnoughColumnsException extends Exception{
	
	private String message;
	
	public NotEnoughColumnsException(String message) {
		super(message);
		this.message = message;
	}
	
	public String toString() {
		return "NotEnoughColumnsException: " + this.message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
