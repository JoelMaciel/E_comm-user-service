package com.joel.users.application.adpters.impl;

import com.joel.users.application.dtos.response.UserDTO;
import com.joel.users.application.mapper.UserMapper;
import com.joel.users.application.ports.ShowUserUseCase;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.exceptions.UserNotFoundException;
import com.joel.users.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@RequiredArgsConstructor
@Service
public class ShowUserUseCaseImpl implements ShowUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Transactional
    @Override
    public UserDTO findById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        return mapper.toDto(user);
    }
}
