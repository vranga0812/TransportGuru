package com.vbs.custom.exceptions.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vbs.custom.exceptions.Error;
import com.vbs.custom.exceptions.VbsException;

/**
 * @author DL
 * Aug 4, 2015
 */

@ControllerAdvice
public class GenericExceptionHandler {

	@ExceptionHandler(VbsException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	public Error handleVbsException(VbsException e){
		//Errors<Error> errors = new Errors<Error>();
		//errors.addError(
		Error error = new Error(e.getCode(), e.getMessage());
		return error;
	}
	
	
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleException(Exception e){
		e.printStackTrace();
	}
	
}
