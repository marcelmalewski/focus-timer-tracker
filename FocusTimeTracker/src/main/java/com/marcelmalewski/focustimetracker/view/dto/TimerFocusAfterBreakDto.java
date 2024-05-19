package com.marcelmalewski.focustimetracker.view.dto;

import com.marcelmalewski.focustimetracker.view.interfaces.TimerBasicFields;

public record TimerFocusAfterBreakDto(
	String timerSelectedTopic,
	Integer timerShortBreak,
	Integer timerLongBreak,
	Boolean timerAutoBreak
) implements TimerBasicFields {
}
