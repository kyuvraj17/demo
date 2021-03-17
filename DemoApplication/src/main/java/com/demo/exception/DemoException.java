package com.demo.exception;

public class DemoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DemoException(String errorMessage) {
		super(errorMessage);
	}

	public DemoException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}
}
