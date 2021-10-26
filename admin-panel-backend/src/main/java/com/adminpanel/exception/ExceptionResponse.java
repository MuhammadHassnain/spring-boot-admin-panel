package com.adminpanel.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@ToString
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@Getter
public class ExceptionResponse {
	
	  private final String message;
	  private String detail;
	  private String hint;
	  private String nextAction;
	  private String support;
	  private final HttpStatus httpStatus;
	  
	public static ExceptionResponse builder(HttpStatus httpStatus, String message) {
		return new ExceptionResponse(httpStatus,message);
	}
	private ExceptionResponse(HttpStatus httpStatus, String message) {
		this.message = message;
		this.detail = null;
		this.hint = null;
		this.nextAction = null;
		this.support = null;
		this.httpStatus = httpStatus;
	}
	
	
	public ExceptionResponse setHint(String hint) {
		this.hint = hint;
		return this;
	}
	public ExceptionResponse setNextAction(String nextAction) {
		this.nextAction = nextAction;
		return this;
	}
	public ExceptionResponse setDetails(String detail) {
		this.detail = detail;
		return this;
	}
	 
	public ExceptionResponse setSupport(String support) { 
		this.support = support;
		return this;
	}
	
	public static ExceptionResponse createReponseFromException(ApplicationException ex) {
		ExceptionResponse response = ExceptionResponse.builder(ex.getHttpStatus(), ex.getMessage())
				.setDetails(ex.getDetail())
				.setHint(ex.getHint())
				.setNextAction(ex.getNextAction())
				.setSupport(ex.getSupport());
		return response;
	}
}
