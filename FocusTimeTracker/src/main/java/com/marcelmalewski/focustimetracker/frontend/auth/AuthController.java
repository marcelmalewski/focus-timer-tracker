package com.marcelmalewski.focustimetracker.frontend.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Tag(name = "Auth", description = "Auth views")
public class AuthController {
	@Operation(summary = "Login view")
	@GetMapping("/login")
	public String getFeed() {
		return "login";
	}

	@Operation(summary = "Login view")
	@GetMapping("/register")
	public String getRegistrationView(Model model) {
		return "register";
	}
}
