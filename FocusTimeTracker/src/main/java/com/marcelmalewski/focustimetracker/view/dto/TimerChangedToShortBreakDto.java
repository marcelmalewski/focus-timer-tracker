package com.marcelmalewski.focustimetracker.view.dto;

// TODO walidacja nulli?
public record TimerChangedToShortBreakDto(
	String selectedTopic,
	String shortBreak,
	String longBreak,
	String timerAutoBreakPretty,
	String interval
) {
}
