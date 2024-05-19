package com.marcelmalewski.focustimetracker.view.interfaces;

public interface TimerFocusAferHome extends TimerBasicFields {
	String timerSelectedTopic();
	Integer timerSetHours();
	Integer timerSetMinutes();
	Integer timerSetSeconds();
	Integer timerShortBreak();
	Integer timerLongBreak();
	Boolean timerAutoBreak();
	Integer timerInterval();
}
