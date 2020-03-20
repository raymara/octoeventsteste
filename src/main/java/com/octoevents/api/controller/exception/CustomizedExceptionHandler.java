package com.octoevents.api.controller.exception;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.octoevents.api.model.Error;
import com.octoevents.api.service.ErrorServiceAction;
import com.octoevents.api.util.SystemMessage;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {
	
	ErrorServiceAction ErrorService;
	
	@Autowired 
	public void setErrorService(ErrorServiceAction ErrorService){ 
		this.ErrorService = ErrorService; 
	}
	
	@ExceptionHandler(Exception.class) 
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		
		Error Error = new Error(ex.getMessage(), request.getDescription(false), new Date(), request.getDescription(true));
		
		ErrorService.saveError(Error);
		
		return new ResponseEntity<Object>(Error, HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	 
	 
	  
	@ExceptionHandler(IssueNotFoundException.class) 
	public final ResponseEntity<Object> handleIssueNotFoundException(IssueNotFoundException ex, WebRequest request) { 
		
		Error Error = new Error(ex.getMessage(), request.getDescription(false), new Date(), request.getDescription(true));
		
		return new ResponseEntity<Object>(Error, HttpStatus.NOT_FOUND); 
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
	  Error Error = new Error(SystemMessage.MESSAGE_NOT_READABLE,
			  										ex.getMessage(), new Date(), request.getDescription(true));
	  
	  ErrorService.saveError(Error);
	  
	  return new ResponseEntity<Object>(Error, HttpStatus.BAD_REQUEST);
	} 
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		 Error Error = new Error(SystemMessage.MEDIA_TYPE_NOT_SUPPORTED_MESSAGE, ex.getMessage(), 
				 										new Date(), request.getDescription(true));
		 
		 ErrorService.saveError(Error);
		  
		  return new ResponseEntity<Object>(Error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	 

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
	  Error Error = new Error(SystemMessage.ARGUMENT_NOT_VALID_EXCEPTION_MESSAGE, 
			  										ex.getBindingResult().toString(), new Date(), request.getDescription(true));
	  
	  ErrorService.saveError(Error);
	  
	  return new ResponseEntity<Object>(Error, HttpStatus.BAD_REQUEST);
	} 
}