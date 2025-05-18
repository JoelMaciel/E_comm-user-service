package com.joel.users.application.ports.usecases.users;

import com.joel.users.application.commands.UpdateUserCommand;
import com.joel.users.domain.entities.User;

public interface UpdateUserUseCase {
    User execute(UpdateUserCommand updateUserCommand);
}
