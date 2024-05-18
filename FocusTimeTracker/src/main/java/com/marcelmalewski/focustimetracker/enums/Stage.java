package com.marcelmalewski.focustimetracker.enums;

public enum Stage {
	HOME("Home"),
	FOCUS("Focus"),
	PAUSE("Pause"),
	SHORT_BREAK("Short break"),
	LONG_BREAK("Long break");

	private final String stageName;

	private Stage(String stageName) {
		this.stageName = stageName;
	}

	public String getStageName() {
		return stageName;
	}
}
