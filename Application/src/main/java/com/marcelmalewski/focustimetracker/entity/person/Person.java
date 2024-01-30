package com.marcelmalewski.focustimetracker.entity.person;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
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

	private boolean autoBreak;
	private int shortBreak;
	private int longBreak;
	private Integer interval;

	//TODO relations
	private String dailyFocusSummaries;
	private String topics;
	private String alarmSettings;

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
}
