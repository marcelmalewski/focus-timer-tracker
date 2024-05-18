package com.marcelmalewski.focustimetracker.view.dto;

public record TimerFocusAfterBreakDto(
	String timerSelectedTopic,
	Integer timerShortBreak,
	Integer timerLongBreak,
	Boolean timerAutoBreak
) implements TimerBasicFields {
}
