package com.marcelmalewski.focustimetracker.entity.person;

import org.springframework.stereotype.Service;

@Service
public class PersonService {
	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public boolean existsByLogin(String login) {
		return personRepository.existsByLogin(login);
	}

	public boolean existsByEmail(String email) {
		return personRepository.existsByEmail(email);
	}

	public void create(Person person) {
		personRepository.save(person);
	}
}
