package com.ctse.quiz_app.exception;

public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 7452761686904563106L;

	public UserNotFoundException(String exception) {
		super(exception);
	}
}
