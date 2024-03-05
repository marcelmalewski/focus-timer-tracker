package com.marcelmalewski.focustimetracker.view;

public record TimerChangedToRunningDto(String remainingTime, String remainingTimeAsString,
																			 String setTimeAsString) {
}
