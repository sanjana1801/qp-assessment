package com.grocery.GroceryApp.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	
	private String message;
	
	public ResourceNotFoundException(String message) {
		super();
		this.message = message;
	}
}