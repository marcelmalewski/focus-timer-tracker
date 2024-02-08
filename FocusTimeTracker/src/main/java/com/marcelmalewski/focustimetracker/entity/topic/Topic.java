package com.marcelmalewski.focustimetracker.entity.topic;

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
@Table(name = "topic")
public class Topic {
	@Id
	@SequenceGenerator(name = "topic_sequence", sequenceName = "topic_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_sequence")
	private Long id;
	@Version
	private Integer version;

	private String name;

	//TODO relations
	private String owner;
	private String subTopics;

	@Override
	public String toString() {
		return "Topic{" +
			"id=" + id +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Topic topic)) return false;

		return getId() != null ? getId().equals(topic.getId()) : topic.getId() == null;
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 0;
	}
}
