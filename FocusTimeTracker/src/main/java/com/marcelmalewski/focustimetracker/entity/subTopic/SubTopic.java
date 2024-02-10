package com.marcelmalewski.focustimetracker.entity.subTopic;

import com.marcelmalewski.focustimetracker.entity.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "subtopic")
public class SubTopic implements Topic {
	@Id
	@SequenceGenerator(name = "subtopic_sequence", sequenceName = "subtopic_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subtopic_sequence")
	private Long id;
	@Version
	private Integer version;

	private String name;

	//TODO relations
	private String mainTopic;

	@Override
	public String toString() {
		return "SubTopic{" +
			"id=" + id +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SubTopic subTopic)) return false;

		return getId() != null ? getId().equals(subTopic.getId()) : subTopic.getId() == null;
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 0;
	}
}
