package com.marcelmalewski.focustimetracker.entity.person;

import com.marcelmalewski.focustimetracker.entity.person.exception.AuthenticatedPersonNotFoundException;
import com.marcelmalewski.focustimetracker.security.util.SecurityHelper;
import com.marcelmalewski.focustimetracker.view.dto.TimerChangedToRunningDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
			return new AuthenticatedPersonNotFoundException();
		});
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

	public void updatePrincipalTimerAutoBreak(@NotNull Long principalId, @NotNull Boolean timerAutoBreak, @NotNull HttpServletRequest request,
																						@NotNull HttpServletResponse response) {
		int numberOfAffectedRows = personRepository.updateTimerAutoBreak(principalId, timerAutoBreak);

		if (numberOfAffectedRows == 0) {
			securityHelper.logoutManually(request, response);
			throw new AuthenticatedPersonNotFoundException();
		}
	}

	// TODO update tylko gdy faktycznie coś się zmieniło?
	public void updatePrincipalChangedTimerToRunning(@NotNull long principalId, @NotNull boolean timerAutoBreak, @NotNull TimerChangedToRunningDto timerChangedToRunningDto, @NotNull HttpServletRequest request,
																									 @NotNull HttpServletResponse response) {
		int numberOfAffectedRows;

		if (timerAutoBreak) {
			numberOfAffectedRows = personRepository.startTimerRunningUpdateWithTimerAutoBreakOn(
				principalId,
				timerChangedToRunningDto.hours(),
				timerChangedToRunningDto.minutes(),
				timerChangedToRunningDto.seconds(),
				timerChangedToRunningDto.shortBreak(),
				timerChangedToRunningDto.longBreak(),
				timerChangedToRunningDto.interval()
			);
		} else {
			numberOfAffectedRows = personRepository.startTimerRunningUpdateWithTimerAutoBreakOff(
				principalId,
				timerChangedToRunningDto.hours(),
				timerChangedToRunningDto.minutes(),
				timerChangedToRunningDto.seconds(),
				timerChangedToRunningDto.shortBreak(),
				timerChangedToRunningDto.longBreak()
			);
		}

		if (numberOfAffectedRows == 0) {
			securityHelper.logoutManually(request, response);
			throw new AuthenticatedPersonNotFoundException();
		}
	}
}
