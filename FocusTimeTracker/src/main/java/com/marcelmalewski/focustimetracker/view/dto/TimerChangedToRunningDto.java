package com.marcelmalewski.focustimetracker.view.dto;

// TODO walidacja shortBreak longBreak interval, timerautobreak
// TODO "On" to powinna byc stała
public record TimerChangedToRunningDto(Integer hours, Integer minutes, Integer seconds, Integer shortBreak,
																			 Integer longBreak, Integer interval, String timerAutoBreakPretty, String selectedTopic) {

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
		if (timerAutoBreakPretty.equals("On")) {
			return true;
		} else {
			return false;
		}
	}
}

