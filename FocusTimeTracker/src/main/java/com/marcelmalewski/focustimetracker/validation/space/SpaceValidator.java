package com.marcelmalewski.focustimetracker.validation.space;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Null;
import org.thymeleaf.util.StringUtils;

public class SpaceValidator implements ConstraintValidator<StringWithoutSpaces, String> {
	@Override
	public boolean isValid(String string, ConstraintValidatorContext context) {
		if (string == null || string.isBlank()) {
			return true;
		}

		String trimmedString = string.trim();
		if(string.length() != trimmedString.length()) {
			return false;
		}

		if(trimmedString.contains(" ")) {
			return false;
		}

		return true;
	}
}
