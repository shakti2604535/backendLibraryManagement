package com.library.manage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException  extends RuntimeException{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String resourceName;
  private String field;
  private Object fieldvalue;
  
  public ResourceNotFoundException() {
	super();
}
public ResourceNotFoundException(String resourceName, String field, Object fieldvalue) {
		super(String.format("%s not found with %s: '%s'",resourceName,field,fieldvalue));
		this.resourceName = resourceName;
		this.field = field;
		this.fieldvalue = fieldvalue;
	}
public String getResourceName() {
	return resourceName;
}
public String getField() {
	return field;
}
public Object getFieldvalue() {
	return fieldvalue;
}
}
