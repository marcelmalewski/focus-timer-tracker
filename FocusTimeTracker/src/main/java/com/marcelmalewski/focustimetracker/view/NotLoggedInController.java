package com.marcelmalewski.focustimetracker.view;

import com.marcelmalewski.focustimetracker.entity.person.registration.RegisterRequestDto;
import com.marcelmalewski.focustimetracker.entity.person.registration.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Tag(name = "NotLoggedIn", description = "Views for not logged in person")
public class NotLoggedInController {
	private final RegisterService registerService;

	public NotLoggedInController(RegisterService registerService) {
		this.registerService = registerService;
	}

	@Operation(summary = "Welcome view")
	@GetMapping("/welcome")
	public String getWelcomeView() {
		return "notloggedin/welcome";
	}

	@Operation(summary = "Login view")
	@GetMapping("/login")
	public String getLoginView() {
		return "notloggedin/login";
	}

	@Operation(summary = "Register view")
	@GetMapping("/register")
	public String getRegistrationView(Model model) {
		RegisterRequestDto registerRequestDto = new RegisterRequestDto();
		model.addAttribute("registerRequestDto", registerRequestDto);

		return "notloggedin/register";
	}

	@Operation(summary = "Register view with validation")
	@PostMapping("/register/validation")
	public String register(@Valid RegisterRequestDto registerRequestDto, BindingResult bindingResult, Model model) {
		// TODO do i need clean this password?
		registerRequestDto.setPassword("");

		if (bindingResult.hasErrors()) {
			model.addAttribute("registerRequestDto", registerRequestDto);
			return "notloggedin/register";
		}

		registerService.register(registerRequestDto);
		return "redirect:/register?success";
	}
}
