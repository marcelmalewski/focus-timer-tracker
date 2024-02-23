package com.marcelmalewski.focustimetracker.entity.person;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PersonController {
	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping(value = "/api/v1/persons")
	public List<Person> findAll() {
		return personService.findAll();
	}
}
