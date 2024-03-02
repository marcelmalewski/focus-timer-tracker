package com.marcelmalewski.focustimetracker.validation.space;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SpaceValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StringWithoutSpaces {
	String message() default "must not contain spaces";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
