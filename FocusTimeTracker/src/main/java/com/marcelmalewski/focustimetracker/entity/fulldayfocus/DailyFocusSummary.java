package com.marcelmalewski.focustimetracker.entity.fulldayfocus;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
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

	//TODO relations
	private String owner;
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

	public int getSummedUpFocusTime() {
		return summedUpFocusTime;
	}

	public void setSummedUpFocusTime(int summedUpFocusTime) {
		this.summedUpFocusTime = summedUpFocusTime;
	}

	public int getNumberOfFinishedFocusSessions() {
		return numberOfFinishedFocusSessions;
	}

	public void setNumberOfFinishedFocusSessions(int numberOfFinishedFocusSessions) {
		this.numberOfFinishedFocusSessions = numberOfFinishedFocusSessions;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getFocusSessions() {
		return focusSessions;
	}

	public void setFocusSessions(String focusSessions) {
		this.focusSessions = focusSessions;
	}
}
