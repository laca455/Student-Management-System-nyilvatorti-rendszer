package com.laca455.studentms.exception;

public class EmailInUseException extends RuntimeException {

	public EmailInUseException(String message) {
		super(message);
	}

}