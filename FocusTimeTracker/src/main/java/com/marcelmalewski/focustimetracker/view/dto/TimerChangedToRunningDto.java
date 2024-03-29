package com.marcelmalewski.focustimetracker.view.dto;

// TODO walidacja shortBreak longBreak interval
public record TimerChangedToRunningDto(Integer hours, Integer minutes, Integer seconds, Integer shortBreak, Integer longBreak, Integer interval, String timerAutoBreak) {

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

	public boolean timerAutoBreakAsBoolean() {
		if (timerAutoBreak.equals("on")) {
			return true;
		} else {
			return false;
		}
	}
}

