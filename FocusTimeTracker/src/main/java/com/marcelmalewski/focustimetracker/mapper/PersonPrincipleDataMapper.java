package com.marcelmalewski.focustimetracker.mapper;

import com.marcelmalewski.focustimetracker.entity.person.Person;
import com.marcelmalewski.focustimetracker.entity.person.dto.PrincipalBasicDataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonPrincipleDataMapper {
	PrincipalBasicDataDto toPrincipalBasicDataDto(Person person);
}
