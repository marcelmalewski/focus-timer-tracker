package com.marcelmalewski.focustimetracker.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AuthenticatedGamerNotFoundException extends RuntimeException {
	public AuthenticatedGamerNotFoundException() {
		super("Authenticated person not found");
	}
}
