package com.centime.assignment.task1service1.exception;

import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
public class ExceptionController {
	private  Logger logger = org.slf4j.LoggerFactory.getLogger(ExceptionController.class);


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(reason = "Method Not Allowd" ,code = HttpStatus.METHOD_NOT_ALLOWED)
	public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException ex) {
			return new ResponseEntity(addTraceIdInExceptionMessage("Get Request is not supported for this service"),  getTraceIdHeader(),HttpStatus.METHOD_NOT_ALLOWED);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(reason = "Bad Request" , code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(
			Exception ex) {

		logger.error("Exception occured :" + ex.getMessage(),ex);

		if(ex instanceof WebClientResponseException)
		{
			WebClientResponseException clientExp = (WebClientResponseException) ex;

			if(clientExp.getStatusCode() == HttpStatus.BAD_REQUEST)
				return new ResponseEntity("Person Field Validation Exception", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(addTraceIdInExceptionMessage("Person Field Validation Exception"), getTraceIdHeader(), HttpStatus.BAD_REQUEST);

	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(value = WebClientResponseException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleWebClientResponseExceptionException(
			Exception ex) {

		logger.error("Exception occured :" + ex.getMessage(),ex);
		
		return new ResponseEntity(addTraceIdInExceptionMessage("Person Field Validation Exception"), getTraceIdHeader(),HttpStatus.BAD_REQUEST);

	}

	private HttpHeaders getTraceIdHeader() {
		HttpHeaders header = new HttpHeaders();
		header.add("TRACE_ID", MDC.get("X-B3-TraceId"));
		return header;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException(
			Throwable ex) {

		return new ResponseEntity(addTraceIdInExceptionMessage("Unprocessable Entity"), getTraceIdHeader(), HttpStatus.UNPROCESSABLE_ENTITY);

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(value = Throwable.class)
	public ResponseEntity<Object> handleThrowable(
			Throwable ex) {

		logger.error("Exception occured :" + ex.getMessage(),ex);
	
		// we can add some more based on use case

		return new ResponseEntity(addTraceIdInExceptionMessage("Internal Server Error"),  getTraceIdHeader(),HttpStatus.INTERNAL_SERVER_ERROR);

	}
	private String addTraceIdInExceptionMessage(String message) {
    return  message + String.format("(traceId: %s)", MDC.get("X-B3-TraceId"));
	}
}
