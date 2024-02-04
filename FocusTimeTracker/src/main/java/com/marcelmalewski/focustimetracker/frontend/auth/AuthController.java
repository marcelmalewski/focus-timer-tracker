package com.marcelmalewski.focustimetracker.frontend.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Tag(name = "Auth", description = "Auth views")
public class AuthController {
	@Operation(summary = "Login view")
	@GetMapping("/login")
	public String getFeed(@ModelAttribute LoginModel loginModel, Model model) {
		model.addAttribute("loginModel", loginModel);
		return "login";
	}
}
