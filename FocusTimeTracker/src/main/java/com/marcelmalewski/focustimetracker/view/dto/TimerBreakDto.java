package com.marcelmalewski.focustimetracker.view.dto;

// TODO walidacja nulli?
public record TimerBreakDto(
	String timerSelectedTopic,
	Integer timerShortBreak,
	Integer timerLongBreak,
	Boolean timerAutoBreak
) implements TimerBasicFields{
}
