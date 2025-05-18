package com.joel.users.application.ports.usecases.users;

import com.joel.users.application.commands.CreateUserCommand;
import com.joel.users.domain.entities.User;

public interface CreateUserUseCase {

    User execute(CreateUserCommand createUserCommand);
}
