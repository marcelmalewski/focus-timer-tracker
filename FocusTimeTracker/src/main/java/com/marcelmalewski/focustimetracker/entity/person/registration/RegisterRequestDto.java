package com.marcelmalewski.focustimetracker.entity.person.registration;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.marcelmalewski.focustimetracker.entity.person.PersonValidationConstants.*;

//TODO add password validation
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
	@Size(min = LOGIN_MIN_SIZE , max = LOGIN_MAX_SIZE)
	@NotBlank
	private String login;
	@Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE)
	@NotBlank
	private String password;
	@Email
	@NotBlank
	private String email;
}
