package org.capg.usermgt.exception;

public class AccountAlreadyExistsException extends RuntimeException{
	public AccountAlreadyExistsException(String message) {
		super(message);
	}
}
