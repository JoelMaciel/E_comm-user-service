package com.joel.users.application.ports;

import com.joel.users.application.dtos.response.UserDTO;

import java.util.UUID;

public interface ShowUserUseCase {

    UserDTO findById(UUID userId);
}
