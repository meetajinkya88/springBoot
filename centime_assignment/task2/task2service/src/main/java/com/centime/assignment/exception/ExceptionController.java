package com.centime.assignment.exception;


import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {

	private  Logger logger = org.slf4j.LoggerFactory.getLogger(ExceptionController.class);

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(value = Throwable.class)
	public ResponseEntity<Object> handleExceptions(
			Exception ex) {
		
		logger.error("Exception occured :" + ex.getMessage(),ex);
		
		if(ex  instanceof ResourceNotFoundException)
			return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
		
		if(ex.getCause() instanceof RollbackException)
		{
			if(ex.getCause().getCause() instanceof ConstraintViolationException)
				return new ResponseEntity("Person Field Validation Exception", HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		
		
		// we can some more based on use case
		
		return new ResponseEntity("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
