package com.roche.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
//@ResponseStatus(HttpStatus.BAD_REQUEST)  // 409
public class ExceptionHandling extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public ExceptionHandling(String exception) {
		super(exception);
	}

}
