package com.marcelmalewski.focustimetracker.view;

public class TimerBoxStageRunningDto {
	private Integer hours;
	private Integer minutes;
	private Integer seconds;

	public TimerBoxStageRunningDto(Integer hours, Integer minutes, Integer seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	public Integer getHours() {
		if(hours == null) {
			return 0;
		}

		return hours;
	}

	public Integer getMinutes() {
		if(minutes == null) {
			return 0;
		}

		return minutes;
	}

	public Integer getSeconds() {
		if(seconds == null) {
			return 0;
		}

		return seconds;
	}
}

