package com.joel.users.application.adapters.impl;

import com.joel.users.application.commands.UserCreateCommand;
import com.joel.users.application.mapper.UserMapper;
import com.joel.users.application.ports.usecases.users.CreateUserUseCase;
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
    public User execute(UserCreateCommand userCreateCommand) {
        userValidator.validateUser(userCreateCommand);

        User user = mapper.toDomainFromCommand(userCreateCommand);

        return userRepository.save(user);
    }
}
