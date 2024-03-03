package com.marcelmalewski.focustimetracker.view;

public record Time(Integer hours, Integer minutes, Integer seconds) {

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

