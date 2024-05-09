package com.marcelmalewski.focustimetracker.view.dto;

public record TimerFocusAfterBreakDto(
	String selectedTopic,
	Integer shortBreak,
	Integer longBreak,
	Boolean timerAutoBreak
) {
}
