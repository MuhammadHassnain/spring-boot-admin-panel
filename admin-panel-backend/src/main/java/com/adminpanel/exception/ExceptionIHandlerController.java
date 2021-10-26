package com.adminpanel.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionIHandlerController {
	
	
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<Object> handleApplcationException(ApplicationException exception) {
		return null;
	}

}
