package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.entity.person.Person;
import com.marcelmalewski.focustimetracker.entity.person.PersonService;
import com.marcelmalewski.focustimetracker.entity.topic.mainTopic.MainTopic;
import com.marcelmalewski.focustimetracker.view.dto.TimerToBreakDto;
import com.marcelmalewski.focustimetracker.view.dto.TimerPauseDto;
import com.marcelmalewski.focustimetracker.view.dto.TimerFromHomeToFocusDto;
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

//TODO pewnie trzeba trochę rzeczy dać do serwisu czy coś hmm
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
		Long principalId = Long.valueOf(principal.getName());
		Person principalData = personService.getPrincipalWithFetchedMainTopics(principalId, request, response);

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

	@PutMapping("/timer/focus")
	public String getTimerBoxStageRunning(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody TimerFromHomeToFocusDto timerFromHomeToFocusDto) {
		long principalId = Long.parseLong(principal.getName());
		personService.updatePrincipalWhenStartFocus(principalId, timerFromHomeToFocusDto.timerAutoBreak(), timerFromHomeToFocusDto, request, response);

		String setTimeAsString = timerFromHomeToFocusDto.hours() + "h " + timerFromHomeToFocusDto.minutes() + "m " + timerFromHomeToFocusDto.seconds() + "s";
		model.addAttribute("setTimeAsString", setTimeAsString);

		String remainigTimeAsString = timerFromHomeToFocusDto.hours() + "h " + timerFromHomeToFocusDto.minutes() + "m " + timerFromHomeToFocusDto.seconds() + "s";
		model.addAttribute("remainingTimeAsString", remainigTimeAsString);

		int remainingTime = (timerFromHomeToFocusDto.hours() * 60 * 60) + (timerFromHomeToFocusDto.minutes() * 60) + timerFromHomeToFocusDto.seconds();
		model.addAttribute("remainingTime", remainingTime);

		model.addAttribute("selectedTopic", timerFromHomeToFocusDto.selectedTopic());
		model.addAttribute("shortBreak", timerFromHomeToFocusDto.shortBreak());
		model.addAttribute("longBreak", timerFromHomeToFocusDto.longBreak());
		model.addAttribute("timerAutoBreak", timerFromHomeToFocusDto.timerAutoBreak());
		model.addAttribute("timerAutoBreakPretty", timerService.timerAutoBreakToPretty(timerFromHomeToFocusDto.timerAutoBreak()));

		return "timer/timerBoxStageFocus";
	}

	@PutMapping("/timer/pause")
	public String getTimerBoxStagePaused(Model model, @RequestBody TimerPauseDto dto) {
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
	public String getTimerBoxStageFocusAfterPause(Model model, @RequestBody TimerPauseDto dto) {
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
	public String getTimerBoxStageFocusAfterBreak(Model model) {
		return "timer/timerBoxStageFocus";
	}

	@PutMapping("/timer/shortBreak")
	public String getTimerBoxStageShortBreak(Model model, @RequestBody TimerToBreakDto dto) {
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
	public String getTimerBoxStageLongBreak(Model model, @RequestBody TimerToBreakDto dto) {
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
