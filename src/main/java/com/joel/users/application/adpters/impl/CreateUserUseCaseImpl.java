package com.joel.users.application.adpters.impl;

import com.joel.users.application.dtos.request.UserRequestDTO;
import com.joel.users.application.dtos.response.UserDTO;
import com.joel.users.application.mapper.UserMapper;
import com.joel.users.application.ports.CreateUserUseCase;
import com.joel.users.application.validator.UserValidator;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
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
