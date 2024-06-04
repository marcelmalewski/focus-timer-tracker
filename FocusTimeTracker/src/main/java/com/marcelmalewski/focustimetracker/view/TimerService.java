package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.entity.person.PersonService;
import com.marcelmalewski.focustimetracker.entity.person.dto.PrincipalBasicDataWithMainTopicsDto;
import com.marcelmalewski.focustimetracker.entity.topic.mainTopic.MainTopic;
import com.marcelmalewski.focustimetracker.view.dto.TimerFocusAfterHomeDto;
import com.marcelmalewski.focustimetracker.view.interfaces.TimerBasicFields;
import com.marcelmalewski.focustimetracker.view.interfaces.TimerFocusFields;
import com.marcelmalewski.focustimetracker.view.dto.TimerPauseDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class TimerService {
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

	public void loadFocusWithFullTime(TimerFocusFields timerFocusFields, Model model) {
		String setTimePretty = timerFocusFields.timerSetHours() + "h " + timerFocusFields.timerSetMinutes() + "m " + timerFocusFields.timerSetSeconds() + "s";
		model.addAttribute("setTimeAsString", setTimePretty);
		model.addAttribute("remainingTimeAsString", setTimePretty);

		int remainingTime = (timerFocusFields.timerSetHours() * 60 * 60) + (timerFocusFields.timerSetMinutes() * 60) + timerFocusFields.timerSetSeconds();
		model.addAttribute("remainingTime", remainingTime);

		loadBasicModelAttributes(model, timerFocusFields);
	}

	public void loadFocusWithRemainingTime(TimerFocusFields timerFocusFields, int remainingTime, Model model) {
		String setTimePretty = timerFocusFields.timerSetHours() + "h " + timerFocusFields.timerSetMinutes() + "m " + timerFocusFields.timerSetSeconds() + "s";
		model.addAttribute("setTimeAsString", setTimePretty);

		int remainingTimeSeconds = remainingTime % 60;
		int remainingTimeMinutes = (remainingTime / 60) % 60;
		int remainingTimeHours = remainingTime / 60 / 60;

		String remainingTimePretty = remainingTimeHours + "h " +
			remainingTimeMinutes + "m " +
			remainingTimeSeconds + "s";

		model.addAttribute("remainingTimeAsString", remainingTimePretty);
		model.addAttribute("remainingTime", remainingTime);

		loadBasicModelAttributes(model, timerFocusFields);
	}

	public void loadPauseBasicModelAttributes(Model model, @NotNull TimerPauseDto dto) {
		model.addAttribute("setTimeAsString", dto.setTimeAsString());
		model.addAttribute("remainingTimeAsString", dto.timerRemainingTimeAsString());
		model.addAttribute("remainingTime", dto.timerRemainingTime());

		model.addAttribute("selectedTopic", dto.timerSelectedTopic());
		model.addAttribute("shortBreak", dto.timerShortBreak());
		model.addAttribute("longBreak", dto.timerLongBreak());
		model.addAttribute("timerAutoBreak", dto.timerAutoBreak());
		model.addAttribute("timerAutoBreakPretty", timerAutoBreakToPretty(dto.timerAutoBreak()));
	}

	public void loadBasicModelAttributes(Model model, @NotNull TimerBasicFields dto) {
		model.addAttribute("selectedTopic", dto.timerSelectedTopic());
		model.addAttribute("shortBreak", dto.timerShortBreak());
		model.addAttribute("longBreak", dto.timerLongBreak());
		model.addAttribute("timerAutoBreak", dto.timerAutoBreak());
		model.addAttribute("timerAutoBreakPretty", timerAutoBreakToPretty(dto.timerAutoBreak()));
	}

	public String timerAutoBreakToPretty(boolean timerAutoBreak) {
		return timerAutoBreak ? "On" : "Off";
	}
}
