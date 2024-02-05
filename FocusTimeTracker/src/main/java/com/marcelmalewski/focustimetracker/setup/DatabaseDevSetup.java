package com.marcelmalewski.focustimetracker.setup;

import com.marcelmalewski.focustimetracker.entity.person.Person;
import com.marcelmalewski.focustimetracker.entity.person.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DatabaseDevSetup implements CommandLineRunner {
	private final PersonService personService;
	private final BCryptPasswordEncoder passwordEncoder;

	public DatabaseDevSetup(PersonService personService, BCryptPasswordEncoder passwordEncoder) {
		this.personService = personService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) {
		Person admin = Person.builder()
			.login("admin")
			.password(passwordEncoder.encode("admin.123"))
			.email("admin@admin.com")
			.timerAutoBreak(false)
			.timerAutoBreak(false)
			.shortBreak(5)
			.longBreak(20)
			.build();

		personService.create(admin);
	}
}
