package com.joel.users.application.validator;

import com.joel.users.application.commands.UserCreateCommand;
import com.joel.users.application.commands.UserUpdateCommand;
import com.joel.users.domain.exceptions.CpfAlreadyExistsException;
import com.joel.users.domain.exceptions.EmailAlreadyExistsException;
import com.joel.users.domain.exceptions.UsernameAlreadyExistsException;
import com.joel.users.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserValidator {

    private static final String MSG_USERNAME_ALREADY_EXISTS = "There is already a user registered with this username";
    private static final String MSG_EMAIL_ALREADY_EXISTS = "There is already a user registered with this email address";
    private static final String MSG_CPF_ALREADY_EXISTS = "There is already a user registered with this cpf";

    private final UserRepository userRepository;

    public void validateUser(UserCreateCommand userCommand) {
        if (existsByUsername(userCommand.username())) {
            throw new UsernameAlreadyExistsException(MSG_USERNAME_ALREADY_EXISTS);
        }

        if (existsByCpf(userCommand.cpf())) {
            throw new CpfAlreadyExistsException(MSG_CPF_ALREADY_EXISTS);
        }

        if (existsByEmail(userCommand.email())) {
            throw new EmailAlreadyExistsException(MSG_EMAIL_ALREADY_EXISTS);
        }
    }

    public void validateUpdateUser(UserUpdateCommand userUpdateCommand, String username) {
        if (existsByUsername(userUpdateCommand.username()) && !userUpdateCommand.username().equals(username)) {
            throw new UsernameAlreadyExistsException(MSG_USERNAME_ALREADY_EXISTS);
        }
    }


    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByCpf(String cpf) {
        return userRepository.existsByCpf(cpf);
    }
}
