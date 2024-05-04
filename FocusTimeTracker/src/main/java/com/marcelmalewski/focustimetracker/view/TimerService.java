package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.view.dto.TimerChangedToBreakDto;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class TimerService {
	public void loadBasicModelAttributesForBreakView(Model model, TimerChangedToBreakDto dto) {
		model.addAttribute("selectedTopic", dto.selectedTopic());
		model.addAttribute("shortBreak", dto.shortBreak());
		model.addAttribute("longBreak", dto.longBreak());
		model.addAttribute("timerAutoBreak", timerAutoBreakToBoolean(dto.timerAutoBreakPretty()));
		model.addAttribute("timerAutoBreakPretty", dto.timerAutoBreakPretty());
	}

	public boolean timerAutoBreakToBoolean(String timerAutoBreakPretty) {
		return timerAutoBreakPretty.equals("On");
	}

	public String timerAutoBreakToPretty(boolean timerAutoBreak) {
		return timerAutoBreak ? "On" : "Off";
	}
}
