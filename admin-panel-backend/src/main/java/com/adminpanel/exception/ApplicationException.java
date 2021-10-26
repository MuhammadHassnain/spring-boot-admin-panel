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
public class ApplicationException extends RuntimeException {

	  private static final long serialVersionUID = 1L;
	  private final String message;
	  private String detail;
	  private String hint;
	  private String nextAction;
	  private String support;
	  private final HttpStatus httpStatus;
	  
	  
	  
	  public static ApplicationException builder(HttpStatus httpStatus, String message) {
		  return new ApplicationException(httpStatus,message);
	  }



	private ApplicationException(HttpStatus httpStatus, String message) {
		this.message = message;
		this.detail = null;
		this.hint = null;
		this.nextAction = null;
		this.support = null;
		this.httpStatus = httpStatus;
	}
	
	
	public ApplicationException setHint(String hint) {
		this.hint = hint;
		return this;
	}
	public ApplicationException setNextAction(String nextAction) {
		this.nextAction = nextAction;
		return this;
	}
	public ApplicationException setDetails(String detail) {
		this.detail = detail;
		return this;
	}
	 
	public ApplicationException setSupport(String support) { 
		this.support = support;
		return this;
	}
	
}
