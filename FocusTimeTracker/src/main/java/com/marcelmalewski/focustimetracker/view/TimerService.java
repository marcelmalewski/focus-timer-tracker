package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.view.dto.TimerBasicFields;
import com.marcelmalewski.focustimetracker.view.dto.TimerPauseDto;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class TimerService {
	public void loadBasicModelAttributes(Model model, TimerBasicFields dto) {
		model.addAttribute("selectedTopic", dto.selectedTopic());
		model.addAttribute("shortBreak", dto.shortBreak());
		model.addAttribute("longBreak", dto.longBreak());
		model.addAttribute("timerAutoBreak", dto.timerAutoBreak());
		model.addAttribute("timerAutoBreakPretty", timerAutoBreakToPretty(dto.timerAutoBreak()));
	}

	public void loadBasicModelAttributesForPause(Model model, TimerPauseDto dto) {
		model.addAttribute("setTimeAsString", dto.setTimeAsString());
		model.addAttribute("remainingTimeAsString", dto.remainingTimeAsString());
		model.addAttribute("remainingTime", dto.remainingTime());

		model.addAttribute("selectedTopic", dto.selectedTopic());
		model.addAttribute("shortBreak", dto.shortBreak());
		model.addAttribute("longBreak", dto.longBreak());
		model.addAttribute("timerAutoBreak", dto.timerAutoBreak());
		model.addAttribute("timerAutoBreakPretty", timerAutoBreakToPretty(dto.timerAutoBreak()));
	}

	public String timerAutoBreakToPretty(boolean timerAutoBreak) {
		return timerAutoBreak ? "On" : "Off";
	}
}
