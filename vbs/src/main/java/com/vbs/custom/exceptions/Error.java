package com.vbs.custom.exceptions;

/**
 * @author DL
 * Jun 21, 2015
 */
public class Error {

	private String name;
	private String message;
	
	public Error(){
		
	}

	/**
	 * @param name
	 * @param message
	 */
	public Error(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
