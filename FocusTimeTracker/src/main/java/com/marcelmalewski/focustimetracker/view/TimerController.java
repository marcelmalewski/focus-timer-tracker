package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.entity.person.PersonService;
import com.marcelmalewski.focustimetracker.entity.person.dto.PrincipalBasicDataDto;
import com.marcelmalewski.focustimetracker.entity.topic.mainTopic.MainTopic;
import com.marcelmalewski.focustimetracker.enums.Stage;
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

@Controller
public class TimerController {
	private final PersonService personService;
	private final TimerService timerService;

	public TimerController(PersonService personService, TimerService timerService) {
		this.personService = personService;
		this.timerService = timerService;
	}

	@Operation(summary = "Timer home view")
	@GetMapping("/timer")
	public String getTimerHomeView(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model) {
		long principalId = Long.parseLong(principal.getName());
		PrincipalBasicDataDto principalBasicData = personService.getPrincipalBasicDataWithFetchedMainTopics(principalId, request, response);



		model.addAttribute("timerAutoBreakPretty", timerService.timerAutoBreakToPretty(principalBasicData.timerAutoBreak()));
		model.addAttribute("timerAutoBreak", principalBasicData.timerAutoBreak());

		model.addAttribute("latestSetTimeHours", principalBasicData.latestSetTimeHours());
		model.addAttribute("latestSetTimeMinutes", principalBasicData.latestSetTimeMinutes());
		model.addAttribute("latestSetTimeSeconds", principalBasicData.latestSetTimeSeconds());

		model.addAttribute("shortBreak", principalBasicData.shortBreak());
		model.addAttribute("longBreak", principalBasicData.longBreak());
		model.addAttribute("interval", principalBasicData.interval());

		List<MainTopic> topics = principalBasicData.mainTopics();

		MainTopic firstTopic = topics.getFirst();
		model.addAttribute("firstTopic", firstTopic);

		topics.removeFirst();
		model.addAttribute("topics", topics);

		return "/timer/timerBase";
	}

	@PutMapping("/timer/focusAfterHome")
	public String getTimerFocusAfterHome(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody TimerFocusAfterHomeDto dto) {
		long principalId = Long.parseLong(principal.getName());
		personService.updatePrincipalWhenStartFocus(principalId, dto.timerAutoBreak(), dto, Stage.FOCUS, request, response);

		String setTimePretty = dto.hours() + "h " + dto.minutes() + "m " + dto.seconds() + "s";
		model.addAttribute("setTimeAsString", setTimePretty);
		model.addAttribute("remainingTimeAsString", setTimePretty);

		int remainingTime = (dto.hours() * 60 * 60) + (dto.minutes() * 60) + dto.seconds();
		model.addAttribute("remainingTime", remainingTime);

		timerService.loadBasicModelAttributes(model, dto);

		return "timer/timerBoxStageFocus";
	}

	@PutMapping("/timer/pause")
	public String getTimerToPause(Model model, @RequestBody TimerPauseDto dto) {
		timerService.loadBasicModelAttributesForPause(model, dto);

		return "timer/timerBoxStagePause";
	}

	@PutMapping("/timer/focusAfterPause")
	public String getTimerFocusAfterPause(Model model, @RequestBody TimerPauseDto dto) {
		timerService.loadBasicModelAttributesForPause(model, dto);

		return "timer/timerBoxStageFocus";
	}

	@PutMapping("/timer/focusAfterBreak")
	public String getTimerFocusAfterBreak(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody TimerFocusAfterBreakDto dto) {
		long principalId = Long.parseLong(principal.getName());
		PrincipalBasicDataDto principalBasicData = personService.getPrincipalBasicData(principalId, request, response);

		String setTimePretty = principalBasicData.latestSetTimeHours() + "h " + principalBasicData.latestSetTimeMinutes() + "m " + principalBasicData.latestSetTimeSeconds() + "s";
		model.addAttribute("setTimeAsString", setTimePretty);
		model.addAttribute("remainingTimeAsString", setTimePretty);

		int remainingTime = (principalBasicData.latestSetTimeHours() * 60 * 60) + (principalBasicData.latestSetTimeMinutes() * 60) + principalBasicData.latestSetTimeSeconds();
		model.addAttribute("remainingTime", remainingTime);

		timerService.loadBasicModelAttributes(model, dto);

		return "timer/timerBoxStageFocus";
	}

	@PutMapping("/timer/shortBreak")
	public String getTimerShortBreak(Model model, @RequestBody TimerBreakDto dto) {
		model.addAttribute("breakType", "shortBreak");
		model.addAttribute("breakTypePretty", Stage.SHORT_BREAK);

		String breakRemainigTimeAsString = dto.shortBreak() + "m " + "0s";

		model.addAttribute("breakSetTime", dto.shortBreak());
		model.addAttribute("breakRemainingTime", dto.shortBreak() * 60);
		model.addAttribute("breakRemainingTimeAsString", breakRemainigTimeAsString);

		timerService.loadBasicModelAttributes(model, dto);

		return "timer/timerBoxStageBreak";
	}

	@PutMapping("/timer/longBreak")
	public String getTimerLongBreak(Model model, @RequestBody TimerBreakDto dto) {
		model.addAttribute("breakType", "longBreak");
		model.addAttribute("breakTypePretty", Stage.LONG_BREAK);

		String breakRemainigTimeAsString = dto.longBreak() + "m " + "0s";

		model.addAttribute("breakSetTime", dto.longBreak());
		model.addAttribute("breakRemainingTime", dto.longBreak() * 60);
		model.addAttribute("breakRemainingTimeAsString", breakRemainigTimeAsString);

		timerService.loadBasicModelAttributes(model, dto);

		return "timer/timerBoxStageBreak";
	}
}
