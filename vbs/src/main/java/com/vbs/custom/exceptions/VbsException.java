package com.vbs.custom.exceptions;

/**
 * @author DL
 * Jul 3, 2015
 */
public class VbsException extends Exception {

	private static final long serialVersionUID = 1L;

	private String code ;
	
	private String message;
	
	
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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

	public VbsException(){
		super();
	}
	
	public VbsException(String message){
		super(message);
	}
	
	public VbsException(String code, String message){
		this.code = code;
		this.message = message;
	}
	
}
