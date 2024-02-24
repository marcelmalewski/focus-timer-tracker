package com.marcelmalewski.focustimetracker.view;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimerController {

	@Operation(summary = "Timer home view")
	@GetMapping("/home/timer")
	public String getTimerHomeView() {
		return "timer/timerHome";
	}
}
