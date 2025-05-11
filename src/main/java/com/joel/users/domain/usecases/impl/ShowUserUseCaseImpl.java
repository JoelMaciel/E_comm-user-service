package com.joel.users.domain.usecases.impl;

import com.joel.users.domain.dtos.response.UserDTO;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.exceptions.UserNotFoundException;
import com.joel.users.domain.mapper.UserMapper;
import com.joel.users.domain.repositories.UserRepository;
import com.joel.users.domain.usecases.ShowUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShowUserUseCaseImpl implements ShowUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public UserDTO findById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        return mapper.toDTO(user);
    }
}
