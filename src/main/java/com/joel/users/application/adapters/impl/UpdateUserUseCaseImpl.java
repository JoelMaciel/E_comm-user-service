package com.joel.users.application.adapters.impl;

import com.joel.users.application.commands.UpdateUserCommand;
import com.joel.users.application.mapper.UserMapper;
import com.joel.users.application.ports.usecases.users.ShowUserUseCase;
import com.joel.users.application.ports.usecases.users.UpdateUserUseCase;
import com.joel.users.application.validator.UserValidator;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepository userRepository;
    private final ShowUserUseCase showUserUseCase;
    private final UserMapper mapper;
    private final UserValidator userValidator;

    @Transactional
    @Override
    public User execute(UpdateUserCommand updateUserCommand) {
        User existingUser = showUserUseCase.findById(updateUserCommand.userId());
        userValidator.validateUpdateUser(updateUserCommand, existingUser.getUsername());

        User updatedUser = mapper.toDomainFromCommand(updateUserCommand, existingUser);

        return userRepository.update(updateUserCommand.userId(), updatedUser);
    }
}
