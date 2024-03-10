package com.marcelmalewski.focustimetracker.entity.person.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AuthenticatedPersonNotFoundException extends RuntimeException {
	public AuthenticatedPersonNotFoundException() {
		super("Authenticated person not found");
	}
}
