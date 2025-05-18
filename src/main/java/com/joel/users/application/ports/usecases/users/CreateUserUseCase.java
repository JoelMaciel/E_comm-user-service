package com.joel.users.application.ports.usecases.users;

import com.joel.users.application.commands.UserCreateCommand;
import com.joel.users.domain.entities.User;

public interface CreateUserUseCase {

    User execute(UserCreateCommand userCreateCommand);
}
