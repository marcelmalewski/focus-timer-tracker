package com.marcelmalewski.focustimetracker.view.dto;

// TODO walidacja nulli?
public record TimerPauseDto(
	String setTimeAsString,
	String remainingTimeAsString,
	Integer remainingTime,
	String selectedTopic,
	String shortBreak,
	String longBreak,
	String timerAutoBreakPretty,
	String interval
) {
}