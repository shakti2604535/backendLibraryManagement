package com.library.manage.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.library.manage.entity.ApiErrors;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String msg  = ex.getMessage();
		List<String>detail =  new ArrayList<String>();
		detail.add("Request methode not supported");
		ApiErrors err = new ApiErrors(msg,detail,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String msg  = ex.getMessage();
		List<String>detail =  new ArrayList<String>();
		detail.add("Request methode not supported");
		ApiErrors err = new ApiErrors(msg,detail,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}


	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String msg  = ex.getMessage();
		List<String>detail =  new ArrayList<String>();
		detail.add("Request methode not supported");
		ApiErrors err = new ApiErrors(msg,detail,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String msg  = ex.getMessage();
		List<String>detail =  new ArrayList<String>();
		detail.add("Request methode not supported");
		ApiErrors err = new ApiErrors(msg,detail,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String msg  = ex.getMessage();
		List<String>detail =  new ArrayList<String>();
		detail.add("Request methode not supported");
		ApiErrors err = new ApiErrors(msg,detail,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String msg  = ex.getMessage();
		List<String>detail =  new ArrayList<String>();
		detail.add("Request methode not supported");
		ApiErrors err = new ApiErrors(msg,detail,status,LocalDateTime.now());
		  
		return ResponseEntity.status(status).body(err);
	}
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String msg  = ex.getMessage();
		List<String>detail =  new ArrayList<String>();
		detail.add("Request methode not supported");
		ApiErrors err = new ApiErrors(msg,detail,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex)
	{
		String msg  = ex.getMessage();
		List<String>detail =  new ArrayList<String>();
		detail.add("response not found");
		ApiErrors err = new ApiErrors(msg,detail,HttpStatus.NOT_FOUND,LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> HandleNoSuchElementException(NoSuchElementException ex)
	{
		
		return new ResponseEntity<String>("No such value presnet in db",HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Exception.class)
	
	public  ResponseEntity<String>handleException( Exception exception) {

		return new ResponseEntity<String>("Something wrong",HttpStatus.BAD_REQUEST);
	}
	

 
}
