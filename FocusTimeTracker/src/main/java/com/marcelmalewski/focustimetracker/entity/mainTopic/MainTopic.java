package com.marcelmalewski.focustimetracker.entity.mainTopic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO you can add subtopicsj
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "maintopic")
public class MainTopic {
	@Id
	@SequenceGenerator(name = "maintopic_sequence", sequenceName = "maintopic_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maintopic_sequence")
	private Long id;
	@Version
	private Integer version;

	private String name;

	//TODO relations
	private String owner;
	private String subTopics;

	@Override
	public String toString() {
		return "MainTopic{" +
			"id=" + id +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MainTopic mainTopic)) return false;

		return getId() != null ? getId().equals(mainTopic.getId()) : mainTopic.getId() == null;
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 0;
	}
}
