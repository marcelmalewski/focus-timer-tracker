package com.marcelmalewski.focustimetracker.entity.alarmsettings;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
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

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
