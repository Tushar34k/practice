package com.qsp.employee.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
