package com.marcelmalewski.focustimetracker.entity.person.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {
	public PersonNotFoundException(Long personId) {
		super("Person not found, person id: " + personId);
	}
}
