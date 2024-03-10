package com.marcelmalewski.focustimetracker.entity.person;

import com.marcelmalewski.focustimetracker.security.exception.AuthenticatedGamerNotFoundException;
import com.marcelmalewski.focustimetracker.security.util.PrincipalExtractor;
import com.marcelmalewski.focustimetracker.security.util.SecurityHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class PersonService {
	private final PersonRepository personRepository;
	private final SecurityHelper securityHelper;

	public PersonService(PersonRepository personRepository, SecurityHelper securityHelper) {
		this.personRepository = personRepository;
		this.securityHelper = securityHelper;
	}

	public Person getPrincipalWithFetchedMainTopics(@NotNull Long principalId, @NotNull HttpServletRequest request,
																									@NotNull HttpServletResponse response) {
		return personRepository.findByIdWithFetchedMainTopics(principalId).orElseThrow(() -> {
			securityHelper.logoutManually(request, response);
			return new AuthenticatedGamerNotFoundException();
		});
	}

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public boolean existsByLogin(String login) {
		return personRepository.existsByLogin(login);
	}

	public boolean existsByEmail(String email) {
		return personRepository.existsByEmail(email);
	}

	public Person create(Person person) {
		return personRepository.save(person);
	}
}
