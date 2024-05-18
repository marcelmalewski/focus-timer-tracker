package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.entity.person.Person;
import com.marcelmalewski.focustimetracker.entity.person.PersonService;
import com.marcelmalewski.focustimetracker.enums.Stage;
import com.marcelmalewski.focustimetracker.view.dto.TimerBasicFields;
import com.marcelmalewski.focustimetracker.view.dto.TimerPauseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class TimerService {
	private final PersonService personService;

	public TimerService(PersonService personService) {
		this.personService = personService;
	}

	public void loadTimerFocusAfterHome(Person principalBasicData, HttpServletRequest request, HttpServletResponse response, Model model) {
//		personService.updatePrincipalWhenStartFocus(principalData.getId(), principalData.getTimerAutoBreak(), dto, Stage.FOCUS, request, response);
//
//		model.addAttribute("setTimeAsString", setTimePretty);
//		model.addAttribute("remainingTimeAsString", setTimePretty);
//		model.addAttribute("remainingTime", remainingTime);
//
//		loadBasicModelAttributes(model, dto);
//
//		return "timer/timerBoxStageFocus";
	}

	public void loadBasicModelAttributes(Model model, @NotNull TimerBasicFields dto) {
		model.addAttribute("selectedTopic", dto.timerSelectedTopic());
		model.addAttribute("shortBreak", dto.timerSelectedTopic());
		model.addAttribute("longBreak", dto.timerLongBreak());
		model.addAttribute("timerAutoBreak", dto.timerAutoBreak());
		model.addAttribute("timerAutoBreakPretty", timerAutoBreakToPretty(dto.timerAutoBreak()));
	}

	public void loadBasicModelAttributesForPause(Model model, @NotNull TimerPauseDto dto) {
		model.addAttribute("setTimeAsString", dto.setTimeAsString());
		model.addAttribute("remainingTimeAsString", dto.remainingTimeAsString());
		model.addAttribute("remainingTime", dto.remainingTime());

		model.addAttribute("selectedTopic", dto.timerSelectedTopic());
		model.addAttribute("shortBreak", dto.timerShortBreak());
		model.addAttribute("longBreak", dto.timerShortBreak());
		model.addAttribute("timerAutoBreak", dto.timerAutoBreak());
		model.addAttribute("timerAutoBreakPretty", timerAutoBreakToPretty(dto.timerAutoBreak()));
	}

	public String timerAutoBreakToPretty(boolean timerAutoBreak) {
		return timerAutoBreak ? "On" : "Off";
	}
}
