package com.vbs.custom.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DL
 * Jun 21, 2015
 * 
 * This class will be returned in the response if there are any validation errors in the request
 */
public class Errors<T> {

	List<T> errors = new ArrayList<>();
	
	public Errors(){
		
	}
	
	public Errors(List<T> errors){
		this.errors = errors;
	}

	/**
	 * @return the errors
	 */
	public List<T> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<T> errors) {
		this.errors = errors;
	}
	
	public void addError(T error){
		errors.add(error);
	}
	
}
