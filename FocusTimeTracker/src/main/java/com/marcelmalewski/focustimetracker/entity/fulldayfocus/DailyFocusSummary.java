package com.marcelmalewski.focustimetracker.entity.fulldayfocus;

import com.marcelmalewski.focustimetracker.entity.person.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "dailyfocussummary")
public class DailyFocusSummary {
	@Id
	@SequenceGenerator(name = "dailyfocussummary_sequence", sequenceName = "dailyfocussummary_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dailyfocussummary_sequence")
	private Long id;
	@Version
	private Integer version;

	private int summedUpFocusTime;
	private int numberOfFinishedFocusSessions;

	@ManyToOne
	@JoinColumn(name = "person_id")
	@NotNull
	private Person owner;
	private String focusSessions;

	@Override
	public String toString() {
		return "DailyFocusSummary{" +
			"id=" + id +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof DailyFocusSummary that)) return false;

		return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 0;
	}
}
