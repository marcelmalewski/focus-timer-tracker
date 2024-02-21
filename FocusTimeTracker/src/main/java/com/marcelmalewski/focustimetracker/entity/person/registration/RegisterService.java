package com.marcelmalewski.focustimetracker.entity.person.registration;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
	public void register(@NotNull RegisterRequestDto registerRequestDto) {}
}
