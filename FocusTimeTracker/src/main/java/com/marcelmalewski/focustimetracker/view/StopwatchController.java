package com.marcelmalewski.focustimetracker.view;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StopwatchController {
	@Operation(summary = "Stopwatch home view")
	@GetMapping("/stopwatch")
	public String getStopwatchHomeView() {
		return "/stopwatch/stopwatchBase";
	}
}
