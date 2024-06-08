package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.entity.person.PersonService;
import com.marcelmalewski.focustimetracker.entity.person.dto.PrincipalBasicDataDto;
import com.marcelmalewski.focustimetracker.entity.person.dto.PrincipalBasicDataWithMainTopicsDto;
import com.marcelmalewski.focustimetracker.enums.Stage;
import com.marcelmalewski.focustimetracker.mapper.PersonDtoMapper;
import com.marcelmalewski.focustimetracker.view.dto.TimerBreakDto;
import com.marcelmalewski.focustimetracker.view.dto.TimerPauseDto;
import com.marcelmalewski.focustimetracker.view.dto.TimerFocusAfterHomeDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
public class TimerController {
	private final PersonService personService;
	private final TimerService timerService;
	private final PersonDtoMapper personDtoMapper;

	public TimerController(PersonService personService, TimerService timerService, PersonDtoMapper personDtoMapper) {
		this.personService = personService;
		this.timerService = timerService;
		this.personDtoMapper = personDtoMapper;
	}

	@Operation(summary = "Timer home view")
	@GetMapping("/timer")
	public String getTimerHomeView(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model) {
		long principalId = Long.parseLong(principal.getName());
		PrincipalBasicDataWithMainTopicsDto principalData = personService.getPrincipalBasicDataWithMainTopics(principalId, request, response);

		return switch (principalData.timerStage()) {
			case FOCUS -> {
				timerService.loadFocusWithRemainingTime(principalData, principalData.timerRemainingTime(), model);
				yield "/timer/timerBaseWithStageFocus";
			}
			case PAUSE -> {
				TimerPauseDto timerPauseDto = personDtoMapper.toTimerPauseDto(principalData);
				timerService.loadPauseModelAttributes(model, timerPauseDto);

				yield "timer/timerBaseWithStagePause";
			}
			case SHORT_BREAK -> {
				timerService.loadShortBreakModelAttributes(model, principalData, Stage.SHORT_BREAK);

				yield "timer/timerBaseWithStageBreak";
			}
			case LONG_BREAK -> {
				timerService.loadLongBreakModelAttributes(model, principalData, Stage.LONG_BREAK);

				yield "timer/timerBaseWithStageBreak";
			}
			default -> {
				timerService.loadHome(principalData, model);
				yield "/timer/timerBaseWithStageHome";
			}
		};
	}

	@PatchMapping("/timer/homeAfterReset")
	public String getHomeAfterReset(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model) {
		long principalId = Long.parseLong(principal.getName());
		PrincipalBasicDataWithMainTopicsDto principalData = personService.getPrincipalBasicDataWithMainTopics(principalId, request, response);

		personService.updatePrincipalTimerStage(principalId, Stage.HOME, request, response);
		timerService.loadHome(principalData, model);

		return "/timer/fragments/timerBoxStageHome";
	}

	@PutMapping("/timer/focusAfterHome")
	public String getTimerFocusAfterHome(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody TimerFocusAfterHomeDto dto) {
		long principalId = Long.parseLong(principal.getName());
		personService.updatePrincipalFocusAfterHome(principalId, dto, request, response);
		timerService.loadFocusWithFullTime(dto, model);

		return "timer/fragments/timerBoxStageFocus";
	}

	@PutMapping("/timer/focusAfterPause")
	public String getTimerFocusAfterPause(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody TimerPauseDto dto) {
		long principalId = Long.parseLong(principal.getName());
		personService.updatePrincipalTimerStage(principalId, Stage.FOCUS, request, response);
		timerService.loadPauseModelAttributes(model, dto);

		return "timer/fragments/timerBoxStageFocus";
	}

	@GetMapping("/timer/focusAfterBreak")
	public String getTimerFocusAfterBreak(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model) {
		long principalId = Long.parseLong(principal.getName());
		PrincipalBasicDataDto principalData = personService.getPrincipalBasicData(principalId, request, response);
		timerService.loadFocusWithFullTime(principalData, model);

		return "timer/fragments/timerBoxStageFocus";
	}

	@PutMapping("/timer/pause")
	public String getTimerToPause(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody TimerPauseDto dto) {
		long principalId = Long.parseLong(principal.getName());
		personService.updatePrincipalTimerStageAndRemainingTime(principalId, Stage.PAUSE, dto.timerRemainingTime(), request, response);
		timerService.loadPauseModelAttributes(model, dto);

		return "timer/fragments/timerBoxStagePause";
	}

	@PutMapping("/timer/shortBreak")
	public String getTimerShortBreak(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody TimerBreakDto dto) {
		long principalId = Long.parseLong(principal.getName());
		personService.updatePrincipalTimerStage(principalId, Stage.SHORT_BREAK, request, response);
		timerService.loadShortBreakModelAttributes(model, dto, Stage.SHORT_BREAK);

		return "timer/fragments/timerBoxStageBreak";
	}

	@PutMapping("/timer/longBreak")
	public String getTimerLongBreak(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody TimerBreakDto dto) {
		long principalId = Long.parseLong(principal.getName());
		personService.updatePrincipalTimerStage(principalId, Stage.LONG_BREAK, request, response);
		timerService.loadLongBreakModelAttributes(model, dto, Stage.LONG_BREAK);

		return "timer/fragments/timerBoxStageBreak";
	}
}
