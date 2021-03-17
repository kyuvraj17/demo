package com.demo.exception;

public class PersonNotFoundException extends DemoException {

	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
	public PersonNotFoundException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}
}
