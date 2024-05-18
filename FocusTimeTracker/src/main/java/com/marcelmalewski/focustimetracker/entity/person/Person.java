package com.marcelmalewski.focustimetracker.entity.person;

import com.marcelmalewski.focustimetracker.converter.StageConverter;
import com.marcelmalewski.focustimetracker.entity.alarmsettings.AlarmSettings;
import com.marcelmalewski.focustimetracker.entity.dailyfocusSummary.DailyFocusSummary;
import com.marcelmalewski.focustimetracker.entity.topic.mainTopic.MainTopic;
import com.marcelmalewski.focustimetracker.enums.Stage;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "person")
public class Person implements UserDetails {
	@Id
	@SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
	private Long id;
	@Version
	private Integer version;

	@Column(unique = true)
	@NotNull
	private String login;
	@NotNull
	private String password;
	@Column(unique = true)
	@NotNull
	private String email;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createdAt;

	// LatestSet
	@NotNull
	@Convert(converter = StageConverter.class)
	@Builder.Default
	private Stage latestSetStage = Stage.HOME;
	@Nullable
	private String latestSelectedTopic;
	@NotNull
	@Builder.Default
	private Integer latestSetTimeHours = 0;
	@NotNull
	@Builder.Default
	private Integer latestSetTimeMinutes = 0;
	@NotNull
	@Builder.Default
	private Integer latestSetTimeSeconds = 0;

	// Break settings
	@NotNull
	@Builder.Default
	private Integer shortBreak = 5;
	@NotNull
	@Builder.Default
	private Integer longBreak = 10;
	@NotNull
	@Builder.Default
	private Boolean timerAutoBreak = false;
	@NotNull
	@Builder.Default
	private Boolean stopWatchAutoBreak = true;
	@NotNull
	@Builder.Default
	private Integer interval = 1;

	@OneToMany(mappedBy = "owner")
	@ToString.Exclude
	@Builder.Default
	@NotNull
	private List<DailyFocusSummary> dailyFocusSummaries = new ArrayList<>();

	@OneToMany(mappedBy = "owner")
	@ToString.Exclude
	@Builder.Default
	@NotNull
	private List<MainTopic> mainTopics = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "alarmsettings_id")
	private AlarmSettings alarmSettings;

	@Override
	public String toString() {
		return "Person{" +
			"id=" + id +
			", login='" + login + '\'' +
			'}';
	}

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getUsername() {
		return this.id.toString();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
