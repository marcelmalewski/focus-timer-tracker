package com.marcelmalewski.focustimetracker.entity.person.dto;

import com.marcelmalewski.focustimetracker.entity.topic.mainTopic.MainTopic;
import com.marcelmalewski.focustimetracker.enums.Stage;
import com.marcelmalewski.focustimetracker.view.interfaces.TimerFocusFields;

import java.util.List;

//TODO walidacja nulli?
public record PrincipalBasicDataWithMainTopicsDto(
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
	Integer timerInterval,
	List<MainTopic> mainTopics
) implements TimerFocusFields {
}
