package com.marcelmalewski.focustimetracker.model;

public record Status(Integer id,
										 String userHandle,
										 String userDisplayName,
										 String content) {
}
