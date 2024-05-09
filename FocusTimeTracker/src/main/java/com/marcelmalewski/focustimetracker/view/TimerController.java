package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.entity.person.Person;
import com.marcelmalewski.focustimetracker.entity.person.PersonService;
import com.marcelmalewski.focustimetracker.entity.person.exception.AuthenticatedPersonNotFoundException;
import com.marcelmalewski.focustimetracker.entity.topic.mainTopic.MainTopic;
import com.marcelmalewski.focustimetracker.security.util.SecurityHelper;
import com.marcelmalewski.focustimetracker.view.dto.TimerBreakDto;
import com.marcelmalewski.focustimetracker.view.dto.TimerFocusAfterBreakDto;
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
import java.util.List;
import java.util.Optional;

//TODO pewnie trzeba trochę rzeczy dać do serwisu czy coś hmm
@Controller
public class TimerController {
	private final PersonService personService;
	private final TimerService timerService;
	private final SecurityHelper securityHelper;

	public TimerController(PersonService personService, TimerService timerService, SecurityHelper securityHelper) {
		this.personService = personService;
		this.timerService = timerService;
		this.securityHelper = securityHelper;
	}

	@Operation(summary = "Timer home view")
	@GetMapping("/timer")
	public String getTimerHomeView(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Long principalId = Long.valueOf(principal.getName());
		Optional<Person> optionalPrincipalData = personService.getPrincipalWithFetchedMainTopics(principalId);

		if (optionalPrincipalData.isEmpty()) {
			securityHelper.logoutManually(request, response);
			throw new AuthenticatedPersonNotFoundException();
		}
		Person principalData = optionalPrincipalData.get();

		model.addAttribute("timerAutoBreakPretty", timerService.timerAutoBreakToPretty(principalData.getTimerAutoBreak()));
		model.addAttribute("timerAutoBreak", principalData.getTimerAutoBreak());

		model.addAttribute("latestSetTimeHours", principalData.getLatestSetTimeHours());
		model.addAttribute("latestSetTimeMinutes", principalData.getLatestSetTimeMinutes());
		model.addAttribute("latestSetTimeSeconds", principalData.getLatestSetTimeSeconds());

		model.addAttribute("shortBreak", principalData.getShortBreak());
		model.addAttribute("longBreak", principalData.getLongBreak());
		model.addAttribute("interval", principalData.getInterval());

		List<MainTopic> topics = principalData.getMainTopics();

		MainTopic firstTopic = topics.getFirst();
		model.addAttribute("firstTopic", firstTopic);

		topics.removeFirst();
		model.addAttribute("topics", topics);

		return "/timer/timerBase";
	}

	@PutMapping("/timer/focusAfterHome")
	public String getTimerFocusAfterHome(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody TimerFocusAfterHomeDto timerFocusAfterHomeDto) {
		long principalId = Long.parseLong(principal.getName());
		//TODO catch excpetion
		personService.updatePrincipalWhenStartFocus(principalId, timerFocusAfterHomeDto.timerAutoBreak(), timerFocusAfterHomeDto, request, response);

		String setTimePretty = timerFocusAfterHomeDto.hours() + "h " + timerFocusAfterHomeDto.minutes() + "m " + timerFocusAfterHomeDto.seconds() + "s";
		model.addAttribute("setTimeAsString", setTimePretty);
		model.addAttribute("remainingTimeAsString", setTimePretty);

		int remainingTime = (timerFocusAfterHomeDto.hours() * 60 * 60) + (timerFocusAfterHomeDto.minutes() * 60) + timerFocusAfterHomeDto.seconds();
		model.addAttribute("remainingTime", remainingTime);

		model.addAttribute("selectedTopic", timerFocusAfterHomeDto.selectedTopic());
		model.addAttribute("shortBreak", timerFocusAfterHomeDto.shortBreak());
		model.addAttribute("longBreak", timerFocusAfterHomeDto.longBreak());
		model.addAttribute("timerAutoBreak", timerFocusAfterHomeDto.timerAutoBreak());
		model.addAttribute("timerAutoBreakPretty", timerService.timerAutoBreakToPretty(timerFocusAfterHomeDto.timerAutoBreak()));

		return "timer/timerBoxStageFocus";
	}

	@PutMapping("/timer/pause")
	public String getTimerToPause(Model model, @RequestBody TimerPauseDto dto) {
		model.addAttribute("setTimeAsString", dto.setTimeAsString());
		model.addAttribute("remainingTimeAsString", dto.remainingTimeAsString());
		model.addAttribute("remainingTime", dto.remainingTime());

		model.addAttribute("selectedTopic", dto.selectedTopic());
		model.addAttribute("shortBreak", dto.shortBreak());
		model.addAttribute("longBreak", dto.longBreak());
		model.addAttribute("timerAutoBreak", dto.timerAutoBreak());
		model.addAttribute("timerAutoBreakPretty", timerService.timerAutoBreakToPretty(dto.timerAutoBreak()));

		return "timer/timerBoxStagePause";
	}

	@PutMapping("/timer/focusAfterPause")
	public String getTimerFocusAfterPause(Model model, @RequestBody TimerPauseDto dto) {
		model.addAttribute("setTimeAsString", dto.setTimeAsString());
		model.addAttribute("remainingTimeAsString", dto.remainingTimeAsString());
		model.addAttribute("remainingTime", dto.remainingTime());

		model.addAttribute("selectedTopic", dto.selectedTopic());
		model.addAttribute("shortBreak", dto.shortBreak());
		model.addAttribute("longBreak", dto.longBreak());
		model.addAttribute("timerAutoBreak", dto.timerAutoBreak());
		model.addAttribute("timerAutoBreakPretty", timerService.timerAutoBreakToPretty(dto.timerAutoBreak()));

		return "timer/timerBoxStageFocus";
	}

	@PutMapping("/timer/focusAfterBreak")
	public String getTimerFocusAfterBreak(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody TimerFocusAfterBreakDto dto) {
		long principalId = Long.parseLong(principal.getName());
		Optional<Person> optionalPrincipalData = personService.getPrincipal(principalId);

		if (optionalPrincipalData.isEmpty()) {
			securityHelper.logoutManually(request, response);
			throw new AuthenticatedPersonNotFoundException();
		}
		Person principalData = optionalPrincipalData.get();

		String setTimePretty = principalData.getLatestSetTimeHours() + "h " + principalData.getLatestSetTimeMinutes() + "m " + principalData.getLatestSetTimeSeconds() + "s";
		model.addAttribute("setTimeAsString", setTimePretty);
		model.addAttribute("remainingTimeAsString", setTimePretty);

		int remainingTime = (principalData.getLatestSetTimeHours() * 60 * 60) + (principalData.getLatestSetTimeMinutes() * 60) + principalData.getLatestSetTimeSeconds();
		model.addAttribute("remainingTime", remainingTime);


		model.addAttribute("selectedTopic", dto.selectedTopic());
		model.addAttribute("shortBreak", dto.shortBreak());
		model.addAttribute("longBreak", dto.longBreak());
		model.addAttribute("timerAutoBreak", dto.timerAutoBreak());
		model.addAttribute("timerAutoBreakPretty", timerService.timerAutoBreakToPretty(dto.timerAutoBreak()));

		return "timer/timerBoxStageFocus";
	}

	@PutMapping("/timer/shortBreak")
	public String getTimerShortBreak(Model model, @RequestBody TimerBreakDto dto) {
		timerService.loadBasicModelAttributesForBreakView(model, dto);

		model.addAttribute("breakType", "shortBreak");
		model.addAttribute("breakTypePretty", "Short break");

		String breakRemainigTimeAsString = dto.shortBreak() + "m " + "0s";

		model.addAttribute("breakSetTime", dto.shortBreak());
		model.addAttribute("breakRemainingTime", dto.shortBreak() * 60);
		model.addAttribute("breakRemainingTimeAsString", breakRemainigTimeAsString);

		return "timer/timerBoxStageBreak";
	}

	@PutMapping("/timer/longBreak")
	public String getTimerLongBreak(Model model, @RequestBody TimerBreakDto dto) {
		timerService.loadBasicModelAttributesForBreakView(model, dto);

		model.addAttribute("breakType", "longBreak");
		model.addAttribute("breakTypePretty", "Long break");

		String breakRemainigTimeAsString = dto.longBreak() + "m " + "0s";

		model.addAttribute("breakSetTime", dto.longBreak());
		model.addAttribute("breakRemainingTime", dto.longBreak() * 60);
		model.addAttribute("breakRemainingTimeAsString", breakRemainigTimeAsString);

		return "timer/timerBoxStageBreak";
	}
}
