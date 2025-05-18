package com.joel.users.application.commands;

import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record EmployeeCommand(
        UUID userId
) {
}
