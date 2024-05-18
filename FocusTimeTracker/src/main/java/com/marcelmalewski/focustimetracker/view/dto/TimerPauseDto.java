package com.marcelmalewski.focustimetracker.view.dto;

// TODO walidacja nulli?
public record TimerPauseDto(
	String setTimeAsString,
	String remainingTimeAsString,
	Integer remainingTime,
	String timerSelectedTopic,
	String timerShortBreak,
	String timerLongBreak,
	Boolean timerAutoBreak
) {
}
