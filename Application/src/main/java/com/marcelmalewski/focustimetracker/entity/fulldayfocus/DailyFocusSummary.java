package com.marcelmalewski.focustimetracker.entity.fulldayfocus;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
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
}
