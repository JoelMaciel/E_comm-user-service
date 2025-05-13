package com.joel.users.application.validator;

import com.joel.users.application.dtos.request.UserRequestDTO;
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


    public void validateUser(UserRequestDTO userRequestDTO) {
        if (existsByUserName(userRequestDTO.getUsername())) {
            throw new UsernameAlreadyExistsException(MSG_USERNAME_ALREADY_EXISTS);
        }

        if (existsByCpf(userRequestDTO.getCpf())) {
            throw new CpfAlreadyExistsException(MSG_CPF_ALREADY_EXISTS);
        }

        if (existsByEmail(userRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(MSG_EMAIL_ALREADY_EXISTS);
        }
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUserName(String userName) {
        return userRepository.existsByUsername(userName);
    }

    public boolean existsByCpf(String cpf) {
        return userRepository.existsByCpf(cpf);
    }

}
