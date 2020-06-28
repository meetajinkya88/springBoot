package com.centime.assignment.task1service3.exception;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {

	private  Logger logger = org.slf4j.LoggerFactory.getLogger(ExceptionController.class);


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(value = Throwable.class)
	public ResponseEntity<Object> handleExceptions(
			Throwable ex) {

		logger.error("Exception occured :" + ex.getMessage(),ex);


		if(ex instanceof MethodArgumentNotValidException)
			return new ResponseEntity("Person Field Validation Exception", HttpStatus.UNPROCESSABLE_ENTITY);

		if(ex instanceof HttpRequestMethodNotSupportedException)
			return new ResponseEntity("Get Request is not supported for this service", HttpStatus.METHOD_NOT_ALLOWED);

		if(ex instanceof HttpMessageNotReadableException)
			return new ResponseEntity("Message Body is Invalid/Missing. Please pass appropriate message body.", HttpStatus.UNPROCESSABLE_ENTITY);



		// we can add some more based on use case

		return new ResponseEntity("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);

	}
}

