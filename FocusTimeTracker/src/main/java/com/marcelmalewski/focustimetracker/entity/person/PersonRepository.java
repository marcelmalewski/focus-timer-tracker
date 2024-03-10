package com.marcelmalewski.focustimetracker.entity.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	@Query("SELECT person FROM Person person " +
		"JOIN FETCH person.mainTopics mainTopics Where person.id = ?1 ")
	Optional<Person> findByIdWithFetchedMainTopics(Long id);

	Optional<Person> findByLoginOrEmail(String login, String email);

	boolean existsByLogin(String login);

	boolean existsByEmail(String email);
}
