package com.marcelmalewski.focustimetracker.entity.person;

import com.marcelmalewski.focustimetracker.entity.person.exception.AuthenticatedPersonNotFoundException;
import com.marcelmalewski.focustimetracker.security.util.SecurityHelper;
import com.marcelmalewski.focustimetracker.view.Stage;
import com.marcelmalewski.focustimetracker.view.dto.TimerFocusAfterHomeDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Validated
public class PersonService {
	private final PersonRepository personRepository;
	private final SecurityHelper securityHelper;

	public PersonService(PersonRepository personRepository, SecurityHelper securityHelper) {
		this.personRepository = personRepository;
		this.securityHelper = securityHelper;
	}

	public Optional<Person> getPrincipal(long principalId) {
		return personRepository.findById(principalId);
	}

	public Optional<Person> getPrincipalWithFetchedMainTopics(long principalId) {
		return personRepository.findByIdWithFetchedMainTopics(principalId);
	}

	public boolean existsByLogin(@NotNull String login) {
		return personRepository.existsByLogin(login);
	}

	public boolean existsByEmail(@NotNull String email) {
		return personRepository.existsByEmail(email);
	}

	public Person create(@NotNull Person person) {
		return personRepository.save(person);
	}

	public void updatePrincipalTimerAutoBreak(
		long principalId,
		boolean timerAutoBreak,
		@NotNull HttpServletRequest request,
		@NotNull HttpServletResponse response
	) throws AuthenticatedPersonNotFoundException {
		int numberOfAffectedRows = personRepository.updateTimerAutoBreak(principalId, timerAutoBreak);

		if (numberOfAffectedRows == 0) {
			securityHelper.logoutManually(request, response);
			throw new AuthenticatedPersonNotFoundException();
		}
	}

	// TODO update tylko gdy faktycznie coś się zmieniło?
	public void updatePrincipalWhenStartFocus(
		long principalId,
		boolean timerAutoBreak,
		@NotNull TimerFocusAfterHomeDto timerFocusAfterHomeDto,
		@NotNull Stage currentStage,
		@NotNull HttpServletRequest request,
		@NotNull HttpServletResponse response
	) throws AuthenticatedPersonNotFoundException {
		int numberOfAffectedRows;

		if (timerAutoBreak) {
			numberOfAffectedRows = personRepository.startTimerRunningUpdateWithTimerAutoBreakOn(
				principalId,
				timerFocusAfterHomeDto.selectedTopic(),
				timerFocusAfterHomeDto.hours(),
				timerFocusAfterHomeDto.minutes(),
				timerFocusAfterHomeDto.seconds(),
				timerFocusAfterHomeDto.shortBreak(),
				timerFocusAfterHomeDto.longBreak(),
				timerFocusAfterHomeDto.interval()
			);
		} else {
			numberOfAffectedRows = personRepository.startTimerRunningUpdateWithTimerAutoBreakOff(
				principalId,
				timerFocusAfterHomeDto.selectedTopic(),
				timerFocusAfterHomeDto.hours(),
				timerFocusAfterHomeDto.minutes(),
				timerFocusAfterHomeDto.seconds(),
				timerFocusAfterHomeDto.shortBreak(),
				timerFocusAfterHomeDto.longBreak()
			);
		}

		if (numberOfAffectedRows == 0) {
			securityHelper.logoutManually(request, response);
			throw new AuthenticatedPersonNotFoundException();
		}
	}
}
