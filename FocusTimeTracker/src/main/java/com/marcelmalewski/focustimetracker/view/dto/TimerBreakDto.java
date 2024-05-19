package com.marcelmalewski.focustimetracker.view.dto;

import com.marcelmalewski.focustimetracker.view.interfaces.TimerBasicFields;

// TODO walidacja nulli?
public record TimerBreakDto(
	String timerSelectedTopic,
	Integer timerShortBreak,
	Integer timerLongBreak,
	Boolean timerAutoBreak,
	Integer timerInterval
) implements TimerBasicFields {
}
