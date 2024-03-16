package com.marcelmalewski.focustimetracker.entity.person;

import com.marcelmalewski.focustimetracker.entity.person.dto.UpdateTimerAutoBreakDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
public class PersonController {
	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@PatchMapping(value = "person/timer-auto-break")
	public void updateTimerAutoBreak(@RequestBody UpdateTimerAutoBreakDto updateTimerAutoBreakDto, Principal principal, HttpServletRequest request, HttpServletResponse response) {
		Long principalId = Long.valueOf(principal.getName());
		boolean timerAutoBreakValue = updateTimerAutoBreakDto.timerAutoBreak() != null;

		personService.updatePrincipalTimerAutoBreak(principalId, timerAutoBreakValue, request, response);
	}
}
