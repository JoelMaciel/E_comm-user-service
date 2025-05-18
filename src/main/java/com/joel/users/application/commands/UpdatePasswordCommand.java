package com.joel.users.application.commands;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UpdatePasswordCommand(
        UUID userId,
        String password,
        String oldPassword
) {
}
