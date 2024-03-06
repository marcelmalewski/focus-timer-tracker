package com.marcelmalewski.focustimetracker.view.dto;

public record TimerChangedToPausedDto(String remainingTime, String remainingTimeAsString,
																			String setTimeAsString) {
}
