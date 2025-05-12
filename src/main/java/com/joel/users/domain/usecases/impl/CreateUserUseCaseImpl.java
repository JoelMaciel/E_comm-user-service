package com.joel.users.domain.usecases.impl;

import com.joel.users.domain.dtos.request.UserRequestDTO;
import com.joel.users.domain.dtos.response.UserDTO;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.mapper.UserMapper;
import com.joel.users.domain.repositories.UserRepository;
import com.joel.users.domain.usecases.CreateUserUseCase;
import com.joel.users.domain.validator.UserValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final UserValidator userValidator;

    @Transactional
    @Override
    public UserDTO createUser(UserRequestDTO userRequestDTO) {
        userValidator.validateUser(userRequestDTO);

        User user = mapper.toEntity(userRequestDTO);

        User userSaved = userRepository.save(user);

        return mapper.toDTO(userSaved);
    }
}
