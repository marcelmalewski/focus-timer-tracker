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
	public String getTime(Model model, @RequestBody TimerBoxStageRunningDto timerBoxStageRunningDto) {
		String timerSetTimeAsString = timerBoxStageRunningDto.hours() + "h " + timerBoxStageRunningDto.minutes() + "m " + timerBoxStageRunningDto.seconds() + "s ";
		model.addAttribute("timerSetTimeAsString", timerSetTimeAsString);

		int timerSetTime = (timerBoxStageRunningDto.hours() * 60 * 60) + (timerBoxStageRunningDto.minutes() * 60) + timerBoxStageRunningDto.seconds();
		model.addAttribute("timerSetTime", timerSetTime);

		return "/timer/timerBoxStageRunning";
	}
}
