package com.marcelmalewski.focustimetracker.entity.person;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "person")
public class Person {
	@Id
	@SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
	private Long id;
	@Version
	private Integer version;

	@Column(unique = true)
	private String login;
	private String password;
	@Column(unique = true)
	private String email;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createdAt;

	private boolean timerAutoBreak;
	private boolean stopWatchAutoBreak;
	private int shortBreak;
	private int longBreak;
	private Integer interval;

	//TODO relations
	private String dailyFocusSummaries;
	private String topics;
	private String alarmSettings;

	@Override
	public String toString() {
		return "Person{" +
			"id=" + id +
			", login='" + login + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Person person)) return false;

		return getId() != null ? getId().equals(person.getId()) : person.getId() == null;
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isTimerAutoBreak() {
		return timerAutoBreak;
	}

	public void setTimerAutoBreak(boolean timerAutoBreak) {
		this.timerAutoBreak = timerAutoBreak;
	}

	public boolean isStopWatchAutoBreak() {
		return stopWatchAutoBreak;
	}

	public void setStopWatchAutoBreak(boolean stopWatchAutoBreak) {
		this.stopWatchAutoBreak = stopWatchAutoBreak;
	}

	public int getShortBreak() {
		return shortBreak;
	}

	public void setShortBreak(int shortBreak) {
		this.shortBreak = shortBreak;
	}

	public int getLongBreak() {
		return longBreak;
	}

	public void setLongBreak(int longBreak) {
		this.longBreak = longBreak;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public String getDailyFocusSummaries() {
		return dailyFocusSummaries;
	}

	public void setDailyFocusSummaries(String dailyFocusSummaries) {
		this.dailyFocusSummaries = dailyFocusSummaries;
	}

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public String getAlarmSettings() {
		return alarmSettings;
	}

	public void setAlarmSettings(String alarmSettings) {
		this.alarmSettings = alarmSettings;
	}
}
