package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.entity.person.Person;
import com.marcelmalewski.focustimetracker.entity.person.PersonService;
import com.marcelmalewski.focustimetracker.entity.topic.mainTopic.MainTopic;
import com.marcelmalewski.focustimetracker.view.dto.TimerChangedToPausedDto;
import com.marcelmalewski.focustimetracker.view.dto.TimerChangedToResumedDto;
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

	public TimerController(PersonService personService) {
		this.personService = personService;
	}

	@Operation(summary = "Timer home view")
	@GetMapping("/timer")
	public String getTimerHomeView(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Long principalId = Long.valueOf(principal.getName());
		Person principalData = personService.getPrincipalWithFetchedMainTopics(principalId, request, response);

		model.addAttribute("timerAutoBreak", principalData.getTimerAutoBreak() ? "On" : "Off");
		model.addAttribute("timerAutoBreakValue", principalData.getTimerAutoBreak());

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

	@PutMapping("/timer/running")
	public String getTimerBoxStageRunning(Principal principal, HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody TimerChangedToRunningDto timerChangedToRunningDto) {
		boolean timerAutoBreak = timerChangedToRunningDto.timerAutoBreakAsBoolean();
		long principalId = Long.parseLong(principal.getName());

		personService.updatePrincipalChangedTimerToRunning(principalId, timerAutoBreak, timerChangedToRunningDto, request, response);

		String setTimeAsString = timerChangedToRunningDto.hours() + "h " + timerChangedToRunningDto.minutes() + "m " + timerChangedToRunningDto.seconds() + "s ";
		model.addAttribute("setTimeAsString", setTimeAsString);

		String remainigTimeAsString = timerChangedToRunningDto.hours() + "h " + timerChangedToRunningDto.minutes() + "m " + timerChangedToRunningDto.seconds() + "s ";
		model.addAttribute("remainingTimeAsString", remainigTimeAsString);

		int remainingTime = (timerChangedToRunningDto.hours() * 60 * 60) + (timerChangedToRunningDto.minutes() * 60) + timerChangedToRunningDto.seconds();
		model.addAttribute("remainingTime", remainingTime);

		model.addAttribute("remainingTime", remainingTime);
		model.addAttribute("remainingTime", remainingTime);
		model.addAttribute("remainingTime", remainingTime);

		return "/timer/timerBoxStageRunning";
	}

	@PutMapping("/timer/resumed")
	public String getTimerBoxStageResumed(Model model, @RequestBody TimerChangedToResumedDto dto) {
		model.addAttribute("setTimeAsString", dto.setTimeAsString());
		model.addAttribute("remainingTimeAsString", dto.remainingTimeAsString());
		model.addAttribute("remainingTime", dto.remainingTime());

		return "/timer/timerBoxStageRunning";
	}

	@PutMapping("/timer/paused")
	public String getTimerBoxStagePaused(Model model, @RequestBody TimerChangedToPausedDto dto) {
		model.addAttribute("setTimeAsString", dto.setTimeAsString());
		model.addAttribute("remainingTimeAsString", dto.remainingTimeAsString());
		model.addAttribute("remainingTime", dto.remainingTime());

		return "/timer/timerBoxStagePaused";
	}
}
