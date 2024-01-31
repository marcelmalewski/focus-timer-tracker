package com.marcelmalewski.focustimetracker.entity.focussession;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "focussession")
public class FocusSession {
	@Id
	@SequenceGenerator(name = "focussession_sequence", sequenceName = "focussession_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "focussession_sequence")
	private Long id;
	@Version
	private Integer version;

	private String sessionType; //timer || stopwatch
	private Boolean finished;

	//TODO relations
	private String topic;
	private String dailyFocusSummary;

	@Override
	public String toString() {
		return "FocusSession{" +
			"id=" + id +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FocusSession that)) return false;

		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
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

	public String getSessionType() {
		return sessionType;
	}

	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDailyFocusSummary() {
		return dailyFocusSummary;
	}

	public void setDailyFocusSummary(String dailyFocusSummary) {
		this.dailyFocusSummary = dailyFocusSummary;
	}
}
