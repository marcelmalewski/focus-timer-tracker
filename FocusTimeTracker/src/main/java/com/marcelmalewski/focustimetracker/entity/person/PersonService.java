package com.marcelmalewski.focustimetracker.entity.person;

import com.marcelmalewski.focustimetracker.security.exception.AuthenticatedGamerNotFoundException;
import com.marcelmalewski.focustimetracker.security.util.PrincipalExtractor;
import com.marcelmalewski.focustimetracker.security.util.SecurityHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class PersonService {
	private final PersonRepository personRepository;
	private final PrincipalExtractor principalExtractor;
	private final SecurityHelper securityHelper;

	public PersonService(PrincipalExtractor principalExtractor, PersonRepository personRepository, SecurityHelper securityHelper) {
		this.principalExtractor = principalExtractor;
		this.personRepository = personRepository;
		this.securityHelper = securityHelper;
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

	public void create(Person person) {
		personRepository.save(person);
	}

	public void throwExceptionAndLogoutIfAuthenticatedGamerNotFound(@NotNull Principal principal, @NotNull HttpServletRequest request,
																																	@NotNull HttpServletResponse response) {
		long principalId = principalExtractor.extractIdFromPrincipal(principal);

		if (!personRepository.existsById(principalId)) {
			securityHelper.logoutManually(request, response);
			throw new AuthenticatedGamerNotFoundException();
		}
	}
}
