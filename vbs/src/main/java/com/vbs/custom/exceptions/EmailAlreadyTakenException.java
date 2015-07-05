package com.vbs.custom.exceptions;

/**
 * @author DL
 * Jun 21, 2015
 * 
 * this exception is thrown in registration usecase when the username is already Taken 
 * 
 */
public class EmailAlreadyTakenException extends AlreadyTakenException {

	
	private static final long serialVersionUID = 1L;

	public EmailAlreadyTakenException() {
		super();
	}
	
	/**
	 * @param string
	 */
	public EmailAlreadyTakenException(String message) {
		super(message);
	}

}
