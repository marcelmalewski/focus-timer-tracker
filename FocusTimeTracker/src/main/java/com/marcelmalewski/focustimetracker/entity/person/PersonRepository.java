package com.marcelmalewski.focustimetracker.entity.person;

import com.marcelmalewski.focustimetracker.enums.Stage;
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
		"SET person.timerSelectedTopic = :timerSelectedTopic," +
		"person.timerSetHours = :timerSetHours," +
		"person.timerSetMinutes = :timerSetMinutes," +
		"person.timerSetSeconds = :timerSetSeconds," +
		"person.timerShortBreak = :timerShortBreak," +
		"person.timerLongBreak = :timerLongBreak," +
		"person.timerInterval = :timerInterval," +
		"person.timerStage = :currentStage " +
		"WHERE person.id = :id")
	@Transactional
	int startTimerRunningUpdateWithTimerAutoBreakOn(
		@Param(value = "id") long id,
		@Param(value = "timerSelectedTopic") String timerSelectedTopic,
		@Param(value = "timerSetHours") Integer hours,
		@Param(value = "timerSetMinutes") Integer minutes,
		@Param(value = "timerSetSeconds") Integer seconds,
		@Param(value = "timerShortBreak") Integer shortBreak,
		@Param(value = "timerLongBreak") Integer longBreak,
		@Param(value = "timerInterval") Integer interval,
		@Param(value = "currentStage") Stage currentStage
	);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Person person " +
		"SET person.timerSelectedTopic = :timerSelectedTopic," +
		"person.timerSetHours = :timerSetHours," +
		"person.timerSetMinutes = :timerSetMinutes," +
		"person.timerSetSeconds = :timerSetSeconds," +
		"person.timerShortBreak = :timerShortBreak," +
		"person.timerLongBreak = :timerLongBreak," +
		"person.timerStage = :currentStage " +
		"WHERE person.id = :id")
	@Transactional
	int startTimerRunningUpdateWithTimerAutoBreakOff(
		@Param(value = "id") long id,
		@Param(value = "timerSelectedTopic") String timerSelectedTopic,
		@Param(value = "timerSetHours") Integer hours,
		@Param(value = "timerSetMinutes") Integer minutes,
		@Param(value = "timerSetSeconds") Integer seconds,
		@Param(value = "timerShortBreak") Integer shortBreak,
		@Param(value = "timerLongBreak") Integer longBreak,
		@Param(value = "currentStage") Stage currentStage
	);
}
