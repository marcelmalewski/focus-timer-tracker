package com.marcelmalewski.focustimetracker.entity.topic.mainTopic;

import com.marcelmalewski.focustimetracker.entity.focussession.FocusSession;
import com.marcelmalewski.focustimetracker.entity.topic.Topic;
import com.marcelmalewski.focustimetracker.entity.person.Person;
import com.marcelmalewski.focustimetracker.entity.topic.subTopic.SubTopic;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "maintopic")
public class MainTopic implements Topic {
	@Id
	@SequenceGenerator(name = "maintopic_sequence", sequenceName = "maintopic_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maintopic_sequence")
	private Long id;
	@Version
	private Integer version;

	@NotNull
	private String name;

	@ManyToOne
	@JoinColumn(name = "person_id")
	@NotNull
	private Person owner;

	@OneToMany(mappedBy = "mainTopic")
	@NotNull
	private List<SubTopic> subTopics = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "focussession_id")
	private FocusSession focusSession;

	public MainTopic(String name, Person owner) {
		this.name = name;
		this.owner = owner;
	}

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
