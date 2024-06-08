package com.marcelmalewski.focustimetracker.entity.person;

import com.marcelmalewski.focustimetracker.entity.person.dto.PrincipalBasicDataDto;
import com.marcelmalewski.focustimetracker.entity.person.dto.PrincipalBasicDataWithMainTopicsDto;
import com.marcelmalewski.focustimetracker.entity.person.exception.AuthenticatedPersonNotFoundException;
import com.marcelmalewski.focustimetracker.mapper.PersonMapper;
import com.marcelmalewski.focustimetracker.security.util.SecurityHelper;
import com.marcelmalewski.focustimetracker.enums.Stage;
import com.marcelmalewski.focustimetracker.view.dto.TimerFocusAfterHomeDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

//AuthenticatedPersonNotFoundException is only for principal methods
@Service
@Validated
public class PersonService {
	private final PersonRepository personRepository;
	private final SecurityHelper securityHelper;
	private final PersonMapper personMapper;

	public PersonService(PersonRepository personRepository, SecurityHelper securityHelper, PersonMapper personMapper) {
		this.personRepository = personRepository;
		this.securityHelper = securityHelper;
		this.personMapper = personMapper;
	}

	public PrincipalBasicDataDto getPrincipalBasicData(long principalId, HttpServletRequest request, HttpServletResponse response) {
		Optional<Person> optionalPerson = personRepository.findById(principalId);

		return switch (optionalPerson.orElse(null)) {
			case null -> {
				securityHelper.logoutManually(request, response);
				throw new AuthenticatedPersonNotFoundException();
			}
			case Person person -> personMapper.toPrincipalBasicDataDto(person);
		};
	}

	public PrincipalBasicDataWithMainTopicsDto getPrincipalBasicDataWithMainTopics(long principalId, HttpServletRequest request, HttpServletResponse response) {
		Optional<Person> optionalPerson = personRepository.findByIdWithFetchedMainTopics(principalId);

		return switch (optionalPerson.orElse(null)) {
			case null -> {
				securityHelper.logoutManually(request, response);
				throw new AuthenticatedPersonNotFoundException();
			}
			case Person person -> personMapper.toPrincipalBasicDataWithMainTopicsDto(person);
		};
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

	public void updatePrincipalTimerStage(
		long principalId,
		@NotNull Stage timerStage,
		@NotNull HttpServletRequest request,
		@NotNull HttpServletResponse response
	) throws AuthenticatedPersonNotFoundException {
		int numberOfAffectedRows = personRepository.updateTimerStage(principalId, timerStage);

		if (numberOfAffectedRows == 0) {
			securityHelper.logoutManually(request, response);
			throw new AuthenticatedPersonNotFoundException();
		}
	}

	public void updatePrincipalTimerStageAndRemainingTime(
		long principalId,
		@NotNull Stage timerStage,
		int timerRemainingTime,
		@NotNull HttpServletRequest request,
		@NotNull HttpServletResponse response
	) throws AuthenticatedPersonNotFoundException {
		int numberOfAffectedRows = personRepository.updateTimerStageAndRemainingTime(principalId, timerStage, timerRemainingTime);

		if (numberOfAffectedRows == 0) {
			securityHelper.logoutManually(request, response);
			throw new AuthenticatedPersonNotFoundException();
		}
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

	// TODO update tylko gdy faktycznie coś się zmieniło
	public void updatePrincipalFocusAfterHome(
		long principalId,
		@NotNull TimerFocusAfterHomeDto dto,
		@NotNull HttpServletRequest request,
		@NotNull HttpServletResponse response
	) throws AuthenticatedPersonNotFoundException {
		int remainingTime = (dto.timerSetHours() * 60 * 60) + (dto.timerSetMinutes() * 60) + dto.timerSetSeconds();
		int numberOfAffectedRows;

		if (dto.timerAutoBreak()) {
			numberOfAffectedRows = personRepository.updatePrincipalFocusAfterHomeWithAutoBreakOn(
				principalId,
				dto.timerSelectedTopic(),
				dto.timerSetHours(),
				dto.timerSetMinutes(),
				dto.timerSetSeconds(),
				dto.timerShortBreak(),
				dto.timerLongBreak(),
				dto.timerInterval(),
				Stage.FOCUS,
				remainingTime
			);
		} else {
			numberOfAffectedRows = personRepository.updatePrincipalFocusAfterHomeWithAutoBreakOff(
				principalId,
				dto.timerSelectedTopic(),
				dto.timerSetHours(),
				dto.timerSetMinutes(),
				dto.timerSetSeconds(),
				dto.timerShortBreak(),
				dto.timerLongBreak(),
				Stage.FOCUS,
				remainingTime
			);
		}

		if (numberOfAffectedRows == 0) {
			securityHelper.logoutManually(request, response);
			throw new AuthenticatedPersonNotFoundException();
		}
	}
}
