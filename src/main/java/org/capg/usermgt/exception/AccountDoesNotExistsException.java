package org.capg.usermgt.exception;

public class AccountDoesNotExistsException extends RuntimeException{
	public AccountDoesNotExistsException(String message) {
		super(message);
	}
}
