package com.marcelmalewski.focustimetracker.entity.alarmsettings;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "alarmsettings")
public class AlarmSettings {
	@Id
	@SequenceGenerator(name = "alarmsettings_sequence", sequenceName = "alarmsettings_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alarmsettings_sequence")
	private Long id;
	@Version
	private Integer version;

	private int volume;

	//TODO relations
	private String owner;

	@Override
	public String toString() {
		return "AlarmSettings{" +
			"id=" + id +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AlarmSettings that)) return false;

		return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 0;
	}
}
