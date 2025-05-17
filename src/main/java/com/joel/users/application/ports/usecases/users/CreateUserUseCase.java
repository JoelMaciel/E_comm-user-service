package com.joel.users.application.ports.usecases.users;

import com.joel.users.application.dtos.request.UserRequestDTO;
import com.joel.users.domain.entities.User;

public interface CreateUserUseCase {

    User createUser(UserRequestDTO userRequestDTO);
}
