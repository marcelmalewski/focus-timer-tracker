package com.marcelmalewski.focustimetracker.entity.person.dto;

import com.marcelmalewski.focustimetracker.entity.topic.mainTopic.MainTopic;
import com.marcelmalewski.focustimetracker.enums.Stage;

import java.util.List;

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
	Integer timerInterval,
	List<MainTopic> mainTopics
) {
}
