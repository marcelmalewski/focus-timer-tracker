package com.marcelmalewski.focustimetracker.view.dto;

public record TimerChangedToResumedDto(
String setTimeAsString,
String remainingTimeAsString,
String remainingTime
) {
}
