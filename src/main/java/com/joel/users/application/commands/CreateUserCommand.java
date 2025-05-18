package com.joel.users.application.commands;

import lombok.Builder;

@Builder
public record CreateUserCommand(
        String username,
        String email,
        String password,
        String fullName,
        String phoneNumber,
        String cpf
) {
}
