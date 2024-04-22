package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.entity.person.Person;
import com.marcelmalewski.focustimetracker.entity.person.PersonService;
import com.marcelmalewski.focustimetracker.entity.topic.mainTopic.MainTopic;
import com.marcelmalewski.focustimetracker.view.dto.TimerChangedToShortBreakDto;
import com.marcelmalewski.focustimetracker.view.dto.TimerPauseDto;
import com.marcelmalewski.focustimetracker.view.dto.TimerChangedToRunningDto;
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

	private String timerAutoBreakToPretty(boolean timerAutoBreak) {
		return timerAutoBreak ? "On" : "Off";
	}

	private boolean timerAutoBreakToBoolean(String timerAutoBreakPretty) {
		return timerAutoBreakPretty.equals("On");
	}

	public TimerController(PersonService personService) {
		this.personService = personService;
	}

	@Operation(summary = "Timer home view")
	@GetMapping("/timer")
	public String getTimerHomeView(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Long principalId = Long.valueOf(principal.getName());
		Person principalData = personService.getPrincipalWithFetchedMainTopics(principalId, request, response);

		model.addAttribute("timerAutoBreakPretty", timerAutoBreakToPretty(principalData.getTimerAutoBreak()));
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
	public String getTimerBoxStageRunning(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody TimerChangedToRunningDto timerChangedToRunningDto) {
		boolean timerAutoBreak = timerAutoBreakToBoolean(timerChangedToRunningDto.timerAutoBreakPretty());
		long principalId = Long.parseLong(principal.getName());

		personService.updatePrincipalChangedTimerToRunning(principalId, timerAutoBreak, timerChangedToRunningDto, request, response);

		String setTimeAsString = timerChangedToRunningDto.hours() + "h " + timerChangedToRunningDto.minutes() + "m " + timerChangedToRunningDto.seconds() + "s ";
		model.addAttribute("setTimeAsString", setTimeAsString);

		String remainigTimeAsString = timerChangedToRunningDto.hours() + "h " + timerChangedToRunningDto.minutes() + "m " + timerChangedToRunningDto.seconds() + "s ";
		model.addAttribute("remainingTimeAsString", remainigTimeAsString);

		int remainingTime = (timerChangedToRunningDto.hours() * 60 * 60) + (timerChangedToRunningDto.minutes() * 60) + timerChangedToRunningDto.seconds();
		model.addAttribute("remainingTime", remainingTime);

		model.addAttribute("selectedTopic", timerChangedToRunningDto.selectedTopic());
		model.addAttribute("shortBreak", timerChangedToRunningDto.shortBreak());
		model.addAttribute("longBreak", timerChangedToRunningDto.longBreak());
		model.addAttribute("timerAutoBreak", timerAutoBreak);
		model.addAttribute("timerAutoBreakPretty", timerChangedToRunningDto.timerAutoBreakPretty());

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
		model.addAttribute("timerAutoBreak", timerAutoBreakToBoolean(dto.timerAutoBreakPretty()));
		model.addAttribute("timerAutoBreakPretty", dto.timerAutoBreakPretty());

		return "timer/timerBoxStagePause";
	}

	@PutMapping("/timer/focusAfterPause")
	public String getTimerBoxStageResumed(Model model, @RequestBody TimerPauseDto dto) {
		model.addAttribute("setTimeAsString", dto.setTimeAsString());
		model.addAttribute("remainingTimeAsString", dto.remainingTimeAsString());
		model.addAttribute("remainingTime", dto.remainingTime());

		model.addAttribute("selectedTopic", dto.selectedTopic());
		model.addAttribute("shortBreak", dto.shortBreak());
		model.addAttribute("longBreak", dto.longBreak());
		model.addAttribute("timerAutoBreak", timerAutoBreakToBoolean(dto.timerAutoBreakPretty()));
		model.addAttribute("timerAutoBreakPretty", dto.timerAutoBreakPretty());

		return "timer/timerBoxStageFocus";
	}

	@PutMapping("/timer/shortBreak")
	public String getTimerBoxStageShortBreak(Model model, @RequestBody TimerChangedToShortBreakDto dto) {
		model.addAttribute("breakSetTime", dto.shortBreak());
		model.addAttribute("breakRemainingTime", dto.shortBreak());

		model.addAttribute("selectedTopic", dto.selectedTopic());
		model.addAttribute("shortBreak", dto.shortBreak());
		model.addAttribute("longBreak", dto.longBreak());
		model.addAttribute("timerAutoBreak", timerAutoBreakToBoolean(dto.timerAutoBreakPretty()));
		model.addAttribute("timerAutoBreakPretty", dto.timerAutoBreakPretty());

		return "/timer/timerBoxStageShortBreak";
	}
}
