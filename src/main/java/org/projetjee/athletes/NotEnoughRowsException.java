package org.projetjee.athletes;

public class NotEnoughRowsException extends Exception{
	
	private String message;
	
	public NotEnoughRowsException(String message) {
		super(message);
		this.message = message;
	}
	
	public String toString() {
		return "NotEnoughRowsException: " + this.message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
