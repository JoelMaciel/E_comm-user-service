package com.joel.users.application.adapters.impl;

import com.joel.users.application.commands.UpdatePasswordCommand;
import com.joel.users.application.mapper.UserMapper;
import com.joel.users.application.ports.usecases.users.UpdatePasswordUseCase;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.exceptions.PasswordMismatchedException;
import com.joel.users.domain.exceptions.UserNotFoundException;
import com.joel.users.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePasswordUseCaseImpl implements UpdatePasswordUseCase {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute(UpdatePasswordCommand updatePasswordCommand) {

        User user = userRepository.findById(updatePasswordCommand.userId())
                .orElseThrow(UserNotFoundException::new);

        validatePassword(updatePasswordCommand, user);

        User userUpdated = mapper.toDomainFromUpdatePasswordCommand(updatePasswordCommand, user);

        userRepository.save(userUpdated);

    }

    private void validatePassword(UpdatePasswordCommand updatePasswordCommand, User user) {
        if (!passwordEncoder.matches(updatePasswordCommand.oldPassword(), user.getPassword())) {
            throw new PasswordMismatchedException();
        }
    }
}
