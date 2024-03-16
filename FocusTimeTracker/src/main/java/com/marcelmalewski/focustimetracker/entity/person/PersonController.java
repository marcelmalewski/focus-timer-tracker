package com.marcelmalewski.focustimetracker.entity.person;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.security.Principal;

@Controller
public class PersonController {
	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@PatchMapping(value = "person/timer-auto-break")
	public void updateTimerAutoBreak(Principal principal, HttpServletRequest request, HttpServletResponse response) {
		Long principalId = Long.valueOf(principal.getName());
		personService.updatePrincipalTimerAutoBreak(principalId, true, request, response);
	}
}
