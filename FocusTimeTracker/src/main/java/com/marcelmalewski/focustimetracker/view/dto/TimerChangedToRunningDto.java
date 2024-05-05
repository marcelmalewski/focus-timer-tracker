package com.marcelmalewski.focustimetracker.view.dto;

// TODO walidacja nulli?
public record TimerChangedToRunningDto(
	Integer hours,
	Integer minutes,
	Integer seconds,
	String selectedTopic,
	Integer shortBreak,
	Integer longBreak,
	Boolean timerAutoBreak,
	Integer interval
) {

	@Override
	public Integer hours() {
		if (hours == null) {
			return 0;
		}

		return hours;
	}

	@Override
	public Integer minutes() {
		if (minutes == null) {
			return 0;
		}

		return minutes;
	}

	@Override
	public Integer seconds() {
		if (seconds == null) {
			return 0;
		}

		return seconds;
	}
}

