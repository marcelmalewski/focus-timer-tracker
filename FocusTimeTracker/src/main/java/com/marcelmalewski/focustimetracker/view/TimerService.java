package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.entity.person.PersonService;
import com.marcelmalewski.focustimetracker.entity.person.dto.PrincipalBasicDataDto;
import com.marcelmalewski.focustimetracker.entity.person.dto.PrincipalBasicDataWithMainTopicsDto;
import com.marcelmalewski.focustimetracker.entity.topic.mainTopic.MainTopic;
import com.marcelmalewski.focustimetracker.enums.Stage;
import com.marcelmalewski.focustimetracker.view.interfaces.TimerBasicFields;
import com.marcelmalewski.focustimetracker.view.interfaces.TimerFocusAferHome;
import com.marcelmalewski.focustimetracker.view.dto.TimerPauseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class TimerService {
	private final PersonService personService;

	public TimerService(PersonService personService) {
		this.personService = personService;
	}


	public void loadHome(PrincipalBasicDataWithMainTopicsDto dto, Model model) {
		model.addAttribute("timerAutoBreakPretty", timerAutoBreakToPretty(dto.timerAutoBreak()));
		model.addAttribute("timerAutoBreak", dto.timerAutoBreak());

		model.addAttribute("timerSetHours", dto.timerSetHours());
		model.addAttribute("timerSetMinutes", dto.timerSetMinutes());
		model.addAttribute("timerSetSeconds", dto.timerSetSeconds());

		model.addAttribute("shortBreak", dto.timerShortBreak());
		model.addAttribute("longBreak", dto.timerLongBreak());
		model.addAttribute("interval", dto.timerInterval());

		List<MainTopic> topics = dto.mainTopics();

		MainTopic firstTopic = topics.getFirst();
		model.addAttribute("firstTopic", firstTopic);

		topics.removeFirst();
		model.addAttribute("topics", topics);
	}

	public void loadTimerFocusAfterHome(TimerFocusAferHome focusAferHome, long principalId, HttpServletRequest request, HttpServletResponse response, Model model) {
		personService.updatePrincipalWhenStartFocus(principalId, focusAferHome.timerAutoBreak(), focusAferHome, Stage.FOCUS, request, response);

		String setTimePretty = focusAferHome.timerSetHours() + "h " + focusAferHome.timerSetMinutes() + "m " + focusAferHome.timerSetSeconds() + "s";
		model.addAttribute("setTimeAsString", setTimePretty);
		model.addAttribute("remainingTimeAsString", setTimePretty);

		int remainingTime = (focusAferHome.timerSetHours() * 60 * 60) + (focusAferHome.timerSetMinutes() * 60) + focusAferHome.timerSetSeconds();
		model.addAttribute("remainingTime", remainingTime);

		loadBasicModelAttributes(model, focusAferHome);
	}

	public void loadBasicModelAttributes(Model model, @NotNull TimerBasicFields dto) {
		model.addAttribute("selectedTopic", dto.timerSelectedTopic());
		model.addAttribute("shortBreak", dto.timerShortBreak());
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
