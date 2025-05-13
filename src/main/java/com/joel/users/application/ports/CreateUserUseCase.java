package com.joel.users.application.ports;

import com.joel.users.application.dtos.request.UserRequestDTO;
import com.joel.users.application.dtos.response.UserDTO;

public interface CreateUserUseCase {

    UserDTO createUser(UserRequestDTO userRequestDTO);
}
