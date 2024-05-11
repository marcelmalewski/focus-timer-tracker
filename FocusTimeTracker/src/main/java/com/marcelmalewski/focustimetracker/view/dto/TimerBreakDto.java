package com.marcelmalewski.focustimetracker.view.dto;

// TODO walidacja nulli?
public record TimerBreakDto(
	String selectedTopic,
	Integer shortBreak,
	Integer longBreak,
	Boolean timerAutoBreak
) implements TimerBasicFields{
}
