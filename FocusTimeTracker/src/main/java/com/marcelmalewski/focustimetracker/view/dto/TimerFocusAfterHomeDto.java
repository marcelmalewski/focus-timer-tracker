package com.marcelmalewski.focustimetracker.view.dto;

// TODO walidacja nulli
public record TimerFocusAfterHomeDto(
	String timerSelectedTopic,
	Integer timerSetHours,
	Integer timerSetMinutes,
	Integer timerSetSeconds,
	Integer shortBreak,
	Integer longBreak,
	Boolean timerAutoBreak,
	Integer interval
) implements TimerBasicFields {

	@Override
	public Integer timerSetHours() {
		if (timerSetHours == null) {
			return 0;
		}

		return timerSetHours;
	}

	@Override
	public Integer timerSetMinutes() {
		if (timerSetMinutes == null) {
			return 0;
		}

		return timerSetMinutes;
	}

	@Override
	public Integer timerSetSeconds() {
		if (timerSetSeconds == null) {
			return 0;
		}

		return timerSetSeconds;
	}

	public Integer timerShortBreak() {
		return shortBreak;
	}

	public Integer timerLongBreak() {
		return shortBreak;
	}
}

