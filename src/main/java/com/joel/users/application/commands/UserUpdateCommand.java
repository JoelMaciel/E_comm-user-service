package com.joel.users.application.commands;

import java.util.UUID;

public record UserUpdateCommand(
        UUID userId,
        String username,
        String fullName,
        String phoneNumber
) {
}
