package com.marcelmalewski.focustimetracker.entity.focussession;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

		return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 0;
	}
}
