package com.demo.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.exception.DemoException;
import com.demo.exception.PersonNotFoundException;
import com.demo.model.ErrorMessage;

@RestControllerAdvice
public class DemoExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(DemoExceptionHandler.class);

	@ExceptionHandler(DemoException.class)
	public ResponseEntity<ErrorMessage> demoException(DemoException demoException) {
		logger.error(demoException.getMessage(), demoException);
		ErrorMessage errorMessage = new ErrorMessage(demoException.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<ErrorMessage> personNotFoundException(PersonNotFoundException exception) {
		logger.error(exception.getMessage(), exception);
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> exception(Exception exception) {
		logger.error(exception.getMessage(), exception);
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
