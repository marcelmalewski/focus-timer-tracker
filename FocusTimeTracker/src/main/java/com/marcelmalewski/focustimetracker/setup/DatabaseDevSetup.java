package com.marcelmalewski.focustimetracker.setup;

import com.marcelmalewski.focustimetracker.entity.person.Person;
import com.marcelmalewski.focustimetracker.entity.person.PersonService;
import com.marcelmalewski.focustimetracker.entity.topic.mainTopic.MainTopic;
import com.marcelmalewski.focustimetracker.entity.topic.mainTopic.MainTopicService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DatabaseDevSetup implements CommandLineRunner {
	private final PersonService personService;
	private final MainTopicService mainTopicService;
	private final BCryptPasswordEncoder passwordEncoder;

	public DatabaseDevSetup(PersonService personService, MainTopicService mainTopicService, BCryptPasswordEncoder passwordEncoder) {
		this.personService = personService;
		this.mainTopicService = mainTopicService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) {
		Person admin = Person.builder()
			.login("admin")
			.password(passwordEncoder.encode("admin.123"))
			.email("admin@admin.com")
			.latestSetTimeHours(1)
			.latestSetTimeMinutes(12)
			.latestSetTimeSeconds(40)
			.timerAutoBreak(false)
			.build();

		Person savedAdmin = personService.create(admin);

		MainTopic programming = new MainTopic("Programming", savedAdmin);
		mainTopicService.create(programming);

		MainTopic selfGrowth = new MainTopic("Self Growth", savedAdmin);
		mainTopicService.create(selfGrowth);
	}
}
