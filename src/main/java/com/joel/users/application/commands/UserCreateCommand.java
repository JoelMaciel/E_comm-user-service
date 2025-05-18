package com.joel.users.application.commands;

public record UserCreateCommand(
        String username,
        String email,
        String password,
        String fullName,
        String phoneNumber,
        String cpf
) {
}
