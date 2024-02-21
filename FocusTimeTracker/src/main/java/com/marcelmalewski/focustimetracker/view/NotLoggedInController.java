package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.entity.person.registration.RegisterRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Tag(name = "Auth", description = "Auth views")
public class NotLoggedInController {
	@Operation(summary = "Welcome view")
	@GetMapping("/welcome")
	public String getWelcomeView() {
		return "welcome";
	}

	@Operation(summary = "Login view")
	@GetMapping("/login")
	public String getLoginView() {
		return "login";
	}

	@Operation(summary = "Register view")
	@GetMapping("/register")
	public String getRegistrationView(Model model) {
		RegisterRequestDto registerRequestDto = new RegisterRequestDto();
		model.addAttribute("registerRequestDto", registerRequestDto);

		return "register";
	}

	@Operation(summary = "Register view with validation")
	@PostMapping("/register")
	public String register(@Valid RegisterRequestDto registerRequestDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("registerRequestDto", registerRequestDto);
			return "register";
		}

		return "login";
	}
}
