package com.joel.users.domain.usecases;

import com.joel.users.domain.dtos.response.UserDTO;

import java.util.UUID;

public interface ShowUserUseCase {

    UserDTO findById(UUID userId);
}
