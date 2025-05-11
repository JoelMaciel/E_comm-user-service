package com.joel.users.domain.usecases;

import com.joel.users.domain.dtos.request.UserRequestDTO;
import com.joel.users.domain.dtos.response.UserDTO;

public interface CreateUserUseCase {

    UserDTO createUser(UserRequestDTO userRequestDTO);
}
