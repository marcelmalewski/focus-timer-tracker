package com.marcelmalewski.focustimetracker.entity.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	Optional<Person> findByLoginOrEmail(String login, String email);
}
