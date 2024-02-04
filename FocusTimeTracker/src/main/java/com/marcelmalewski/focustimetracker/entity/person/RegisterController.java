package com.marcelmalewski.focustimetracker.entity.person;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1")
@Tag(
	name = "Registration v1",
	description = "Person registration v1. Login, and logout are handled by Spring Security"
)
public class RegisterController {
}
