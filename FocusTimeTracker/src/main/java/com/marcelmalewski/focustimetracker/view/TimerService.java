package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.view.dto.TimerToBreakDto;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class TimerService {
	public void loadBasicModelAttributesForBreakView(Model model, TimerToBreakDto dto) {
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
