package com.marcelmalewski.focustimetracker.view.dto;

import com.marcelmalewski.focustimetracker.view.interfaces.TimerBasicFields;

// TODO validate nulls?
public record TimerBreakDto(
	String timerSelectedTopic,
	Integer timerShortBreak,
	Integer timerLongBreak,
	Boolean timerAutoBreak
) implements TimerBasicFields {
}
