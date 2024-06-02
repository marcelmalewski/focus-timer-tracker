package com.marcelmalewski.focustimetracker.view.dto;

// TODO walidacja nulli?
public record TimerPauseDto(
	String setTimeAsString,
	String timerRemainingTimeAsString,
	Integer timerRemainingTime,
	String timerSelectedTopic,
	String timerShortBreak,
	String timerLongBreak,
	Boolean timerAutoBreak
) {
}
