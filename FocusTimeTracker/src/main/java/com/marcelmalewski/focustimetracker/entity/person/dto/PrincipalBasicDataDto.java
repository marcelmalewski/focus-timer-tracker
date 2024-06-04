package com.marcelmalewski.focustimetracker.entity.person.dto;

import com.marcelmalewski.focustimetracker.enums.Stage;
import com.marcelmalewski.focustimetracker.view.interfaces.TimerFocusFields;

//TODO walidacja?
public record PrincipalBasicDataDto(
	Long id,
	String login,
	String email,
	Stage timerStage,
	String timerSelectedTopic,
	Integer timerSetHours,
	Integer timerSetMinutes,
	Integer timerSetSeconds,
	Integer timerShortBreak,
	Integer timerLongBreak,
	Boolean timerAutoBreak,
	Boolean stopWatchAutoBreak,
	Integer timerRemainingTime,
	Integer timerInterval
) implements TimerFocusFields {
}
