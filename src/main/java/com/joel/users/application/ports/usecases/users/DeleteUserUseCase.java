package com.joel.users.application.ports.usecases.users;

import java.util.UUID;

public interface DeleteUserUseCase {

    void execute(UUID id);
}
