package com.vbs.custom.exceptions;

import org.springframework.validation.BindingResult;


/**
 * @author DL
 * Jun 19, 2015
 * 
 * this exception will be thrown whenever there is some kind of data related errors
 */



public class InvalidDataException extends VbsException {

	private static final long serialVersionUID = 2249038805559892609L;
	
	BindingResult validationError;
	
	

	/**
	 * @return the validationError
	 */
	public BindingResult getValidationError() {
		return validationError;
	}

	/**
	 * @param validationError the validationError to set
	 */
	public void setValidationError(BindingResult validationError) {
		this.validationError = validationError;
	}

	public InvalidDataException(){
		
		this("Invalid data");
		
	}
	
	public InvalidDataException(String message){
		
		super(message);
		
	}
	
}
