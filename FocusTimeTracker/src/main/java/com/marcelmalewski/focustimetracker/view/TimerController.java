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
				timerService.loadPauseBasicModelAttributes(model, timerPauseDto);

				yield "timer/timerBaseWithStagePause";
			}
			default -> {
				timerService.loadHome(principalData, model);
				yield "/timer/timerBaseWithStageHome";
			}
		};
	}

	@PutMapping("/timer/homeAfterReset")
	public String getHomeAfterReset(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model) {
		long principalId = Long.parseLong(principal.getName());
		PrincipalBasicDataWithMainTopicsDto principalData = personService.getPrincipalBasicDataWithMainTopics(principalId, request, response);

		personService.updatePrincipalTimerStage(principalId, Stage.HOME, request, response);
		timerService.loadHome(principalData, model);

		return "/timer/timerBaseWithStageHome";
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
		timerService.loadPauseBasicModelAttributes(model, dto);

		return "timer/fragments/timerBoxStageFocus";
	}

	@GetMapping("/timer/focusAfterBreak")
	public String getTimerFocusAfterBreak(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model) {
		long principalId = Long.parseLong(principal.getName());
		PrincipalBasicDataDto principalData = personService.getPrincipalBasicData(principalId, request, response);
		timerService.loadFocusWithFullTime(principalData, model);

		return "timer/fragments/timerBoxStageFocus";
	}

	// TODO update remainingTime
	@PutMapping("/timer/pause")
	public String getTimerToPause(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody TimerPauseDto dto) {
		long principalId = Long.parseLong(principal.getName());
		personService.updatePrincipalWhenPause(principalId, dto.timerRemainingTime(), request, response);
		timerService.loadPauseBasicModelAttributes(model, dto);

		return "timer/fragments/timerBoxStagePause";
	}

	// TODO update remainingTime
	@PutMapping("/timer/shortBreak")
	public String getTimerShortBreak(Model model, @RequestBody TimerBreakDto dto) {
		model.addAttribute("breakType", "shortBreak");
		model.addAttribute("breakTypePretty", Stage.SHORT_BREAK.getStageName());

		String breakRemainigTimeAsString = dto.timerShortBreak() + "m " + "0s";

		model.addAttribute("breakSetTime", dto.timerShortBreak());
		model.addAttribute("breakRemainingTime", dto.timerShortBreak() * 60);
		model.addAttribute("breakRemainingTimeAsString", breakRemainigTimeAsString);

		timerService.loadBasicModelAttributes(model, dto);

		return "timer/fragments/timerBoxStageBreak";
	}

	// TODO update remainingTime
	@PutMapping("/timer/longBreak")
	public String getTimerLongBreak(Model model, @RequestBody TimerBreakDto dto) {
		model.addAttribute("breakType", "longBreak");
		model.addAttribute("breakTypePretty", Stage.LONG_BREAK.getStageName());

		String breakRemainigTimeAsString = dto.timerLongBreak() + "m " + "0s";

		model.addAttribute("breakSetTime", dto.timerLongBreak());
		model.addAttribute("breakRemainingTime", dto.timerLongBreak() * 60);
		model.addAttribute("breakRemainingTimeAsString", breakRemainigTimeAsString);

		timerService.loadBasicModelAttributes(model, dto);

		return "timer/fragments/timerBoxStageBreak";
	}
}
