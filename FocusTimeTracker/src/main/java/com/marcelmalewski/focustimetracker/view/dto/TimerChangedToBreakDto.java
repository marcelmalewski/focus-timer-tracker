package com.marcelmalewski.focustimetracker.view.dto;

// TODO walidacja nulli?
public record TimerChangedToBreakDto(
	String selectedTopic,
	Integer shortBreak,
	Integer longBreak,
	Boolean timerAutoBreak,
	String interval
) {
}
