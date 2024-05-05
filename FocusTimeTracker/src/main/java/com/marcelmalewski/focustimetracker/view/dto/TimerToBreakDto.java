package com.marcelmalewski.focustimetracker.view.dto;

// TODO walidacja nulli?
public record TimerToBreakDto(
	String selectedTopic,
	Integer shortBreak,
	Integer longBreak,
	Boolean timerAutoBreak,
	String interval
) {
}
