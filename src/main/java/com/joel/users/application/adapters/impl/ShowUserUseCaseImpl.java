package com.joel.users.application.adapters.impl;

import com.joel.users.application.ports.usecases.users.ShowUserUseCase;
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

    @Transactional(readOnly = true)
    @Override
    public User findById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }
}
