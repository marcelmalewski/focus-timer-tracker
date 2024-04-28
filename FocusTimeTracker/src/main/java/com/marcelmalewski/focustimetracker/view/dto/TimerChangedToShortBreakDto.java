package com.marcelmalewski.focustimetracker.view.dto;

// TODO walidacja nulli?
public record TimerChangedToShortBreakDto(
	String selectedTopic,
	Integer shortBreak,
	Integer longBreak,
	String timerAutoBreakPretty,
	String interval
) {
}
