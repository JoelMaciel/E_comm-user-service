package com.joel.users.application.ports.usecases.users;

import com.joel.users.domain.entities.User;

import java.util.UUID;

public interface ShowUserUseCase {

    User findById(UUID userId);
}
