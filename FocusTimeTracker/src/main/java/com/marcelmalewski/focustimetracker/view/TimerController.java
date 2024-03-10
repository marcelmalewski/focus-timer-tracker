package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.entity.person.Person;
import com.marcelmalewski.focustimetracker.entity.person.PersonService;
import com.marcelmalewski.focustimetracker.entity.topic.mainTopic.MainTopic;
import com.marcelmalewski.focustimetracker.entity.topic.mainTopic.MainTopicService;
import com.marcelmalewski.focustimetracker.view.dto.TimerChangedToPausedDto;
import com.marcelmalewski.focustimetracker.view.dto.TimerChangedToResumedDto;
import com.marcelmalewski.focustimetracker.view.dto.TimerChangedToRunningDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.ArrayList;
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
		personService.throwExceptionAndLogoutIfAuthenticatedGamerNotFound(principal, request, response);

		Long principalId = Long.valueOf(principal.getName());
		Person principalData = personService.getPersonWithFetchedMainTopics(principalId);

		model.addAttribute("latestSetTimeHours", principalData.getLatestSetTimeHours());
		model.addAttribute("latestSetTimeMinutes", principalData.getLatestSetTimeMinutes());
		model.addAttribute("latestSetTimeSeconds", principalData.getLatestSetTimeSeconds());

		List<MainTopic> topics = principalData.getMainTopics();

		MainTopic firstTopic = topics.getFirst();
		model.addAttribute("firstTopic", firstTopic);

		topics.removeFirst();
		model.addAttribute("topics", topics);

		return "/timer/timerBase";
	}

	@PutMapping("/timer/running")
	public String getTimerBoxStageRunning(Model model, @RequestBody TimerChangedToRunningDto timerChangedToRunningDto) {
		String setTimeAsString = timerChangedToRunningDto.hours() + "h " + timerChangedToRunningDto.minutes() + "m " + timerChangedToRunningDto.seconds() + "s ";
		model.addAttribute("setTimeAsString", setTimeAsString);

		String remainigTimeAsString = timerChangedToRunningDto.hours() + "h " + timerChangedToRunningDto.minutes() + "m " + timerChangedToRunningDto.seconds() + "s ";
		model.addAttribute("remainingTimeAsString", remainigTimeAsString);

		int remainingTime = (timerChangedToRunningDto.hours() * 60 * 60) + (timerChangedToRunningDto.minutes() * 60) + timerChangedToRunningDto.seconds();
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
