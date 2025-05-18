package com.joel.users.application.commands;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UpdateUserCommand(
        UUID userId,
        String username,
        String fullName,
        String phoneNumber
) {
}
