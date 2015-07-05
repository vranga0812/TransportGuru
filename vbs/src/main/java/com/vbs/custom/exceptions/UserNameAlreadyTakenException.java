package com.vbs.custom.exceptions;

/**
 * @author DL
 * Jun 21, 2015
 * 
 * this exception is thrown in registration usecase when the username is already Taken 
 * 
 */
public class UserNameAlreadyTakenException extends AlreadyTakenException {

	
	private static final long serialVersionUID = 1L;

	public UserNameAlreadyTakenException() {
		super();
	}
	
	/**
	 * @param string
	 */
	public UserNameAlreadyTakenException(String message) {
		super(message);
	}

}
