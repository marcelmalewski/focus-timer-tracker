package com.marcelmalewski.focustimetracker.entity.person.registration;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO temp size validations values
//TODO add password validation
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
	@Size(min = 3, max = 20)
	@NotBlank
	private String login;
	@Size(min = 3, max = 20)
	@NotBlank
	private String password;
	@Email
	@NotBlank
	private String email;
}