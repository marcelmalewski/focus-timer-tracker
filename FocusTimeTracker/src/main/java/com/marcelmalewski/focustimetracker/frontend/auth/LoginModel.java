package com.marcelmalewski.focustimetracker.frontend.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginModel {
	private String loginOrEmail;
	private String password;
}
