package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.view.dto.TimerChangedToPausedDto;
import com.marcelmalewski.focustimetracker.view.dto.TimerChangedToResumedDto;
import com.marcelmalewski.focustimetracker.view.dto.TimerChangedToRunningDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TimerController {
	@Operation(summary = "Timer home view")
	@GetMapping("/timer")
	public String getTimerHomeView(Model model) {
		Integer latestTimerTime = 325;
		model.addAttribute("latestTimerTime", latestTimerTime);

		List<String> topics = new ArrayList<>();
		topics.add("Programowanie");
		topics.add("Rozwój");

		String firstTopic = topics.getFirst();
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
