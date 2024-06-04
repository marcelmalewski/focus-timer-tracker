package com.marcelmalewski.focustimetracker.view.interfaces;

public interface TimerFocusFields extends TimerBasicFields {
	String timerSelectedTopic();
	Integer timerSetHours();
	Integer timerSetMinutes();
	Integer timerSetSeconds();
	Integer timerShortBreak();
	Integer timerLongBreak();
	Boolean timerAutoBreak();
}
