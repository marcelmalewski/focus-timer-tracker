package com.marcelmalewski.focustimetracker.entity.person;

import org.springframework.stereotype.Service;

@Service
public class PersonService {
	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Person create(Person person) {
		return personRepository.save(person);
	}
}
