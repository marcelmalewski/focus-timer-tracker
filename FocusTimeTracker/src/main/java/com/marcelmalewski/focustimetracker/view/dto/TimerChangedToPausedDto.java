package com.marcelmalewski.focustimetracker.view.dto;

// TODO walidacja nulli?
public record TimerChangedToPausedDto(
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
