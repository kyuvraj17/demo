package com.demo.model;

public class ErrorMessage {

	private String errorMessage;

	public ErrorMessage(String userMessage) {
		super();
		this.errorMessage = userMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
