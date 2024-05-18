package com.marcelmalewski.focustimetracker.entity.person.dto;

import com.marcelmalewski.focustimetracker.entity.topic.mainTopic.MainTopic;
import com.marcelmalewski.focustimetracker.enums.Stage;

import java.util.List;

//TODO walidacja?
public record PrincipalBasicDataDto(
	Long id,
	String login,
	String email,
	Stage latestSetStage,
	String latestSelectedTopic,
	Integer latestSetTimeHours,
	Integer latestSetTimeMinutes,
	Integer latestSetTimeSeconds,
	Integer shortBreak,
	Integer longBreak,
	Boolean timerAutoBreak,
	Boolean stopWatchAutoBreak,
	Integer interval,
	List<MainTopic> mainTopics
) {
}
