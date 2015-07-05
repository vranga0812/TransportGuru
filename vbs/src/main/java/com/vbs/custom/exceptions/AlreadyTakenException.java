package com.vbs.custom.exceptions;


/**
 * @author DL
 * Jun 21, 2015
 * 
 * this exception is super class exceptions for the exception types like UsernameAlready taken, Email already taken exception etc
 * 
 */
public class AlreadyTakenException extends VbsException {

	private static final long serialVersionUID = 1L;
	
	/**
	 *this will store the list of object that can used to set some object and retrieve on the handling method 
	 */
	//private Map<String, Object> objects = new HashMap<>();
	
	public AlreadyTakenException(){
		super();
	}
	
	public AlreadyTakenException(String message){
		super(message);
	}
	
	/**
	 * this method return the object stored in the map for the specific key 
	 */
	public void getObject(String key){
		
	}

}
