package com.marcelmalewski.focustimetracker.entity.person.registration;

import jakarta.validation.constraints.NotNull;

public record RegisterRequestDto(
  @NotNull
  String login,
  @NotNull
  String password,
  @NotNull
  String email
) {
}
