package com.marcelmalewski.focustimetracker.entity.person;

import com.marcelmalewski.focustimetracker.view.dto.TimerChangedToRunningDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	@Query("SELECT person FROM Person person " +
		"JOIN FETCH person.mainTopics mainTopics Where person.id = ?1 ")
	Optional<Person> findByIdWithFetchedMainTopics(Long id);

	Optional<Person> findByLoginOrEmail(String login, String email);

	boolean existsByLogin(String login);

	boolean existsByEmail(String email);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Person person SET person.timerAutoBreak = :timerAutoBreak WHERE person.id = :id")
	@Transactional
	int updateTimerAutoBreak(@Param(value = "id") long id, @Param(value = "timerAutoBreak") boolean timerAutoBreak);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Person person " +
		"SET person.latestSetTimeHours = :hours," +
		"person.latestSetTimeMinutes = :minutes," +
		"person.latestSetTimeSeconds = :seconds," +
		"person.shortBreak = :shortBreak," +
		"person.longBreak = :longBreak," +
		"person.interval = :interval " +
		"WHERE person.id = :id")
	@Transactional
	int updatePrincipalAfterStartTimerRunning(@Param(value = "id") long id,
																						@Param(value = "hours") Integer hours,
																						@Param(value = "minutes") Integer minutes,
																						@Param(value = "seconds") Integer seconds,
																						@Param(value = "shortBreak") Integer shortBreak,
																						@Param(value = "longBreak") Integer longBreak,
																						@Param(value = "interval") Integer interval
	);
}
