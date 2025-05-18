package com.joel.users.application.ports.usecases.users;

import com.joel.users.application.commands.UserUpdateCommand;
import com.joel.users.domain.entities.User;

public interface UpdateUserUseCase {
    User execute(UserUpdateCommand userUpdateCommand);
}
