package com.marcelmalewski.focustimetracker.entity.person.registration;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO temp size validations values
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
	@Size(min = 3, max = 20)
	@NotBlank
	public String login;
	@Size(min = 3, max = 20)
	@NotBlank
	public String password;
	@Email
	@NotBlank
	public String email;
}
