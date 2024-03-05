package com.marcelmalewski.focustimetracker.view;

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
	public String getTimerBoxStageRunning(Model model, @RequestBody Time time) {
		String setTimeAsString = time.hours() + "h " + time.minutes() + "m " + time.seconds() + "s ";
		model.addAttribute("setTimeAsString", setTimeAsString);

		String remainigTimeAsString = time.hours() + "h " + time.minutes() + "m " + time.seconds() + "s ";
		model.addAttribute("remainingTimeAsString", remainigTimeAsString);

		int remainingTime = (time.hours() * 60 * 60) + (time.minutes() * 60) + time.seconds();
		model.addAttribute("remainingTime", remainingTime);

		return "/timer/timerBoxStageRunning";
	}

	@PutMapping("/timer/paused")
	public String getTimerBoxStagePaused(Model model, @RequestBody TimerChangedToRunningDto dto) {
		model.addAttribute("setTimeAsString", dto.setTimeAsString());
		model.addAttribute("remainingTimeAsString", dto.remainingTimeAsString());

		return "/timer/timerBoxStagePaused";
	}
}
