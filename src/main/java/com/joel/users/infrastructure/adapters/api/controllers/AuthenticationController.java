package com.joel.users.infrastructure.adapters.api.controllers;

import com.joel.users.application.commands.CreateUserCommand;
import com.joel.users.application.dtos.request.CreateUserRequestDTO;
import com.joel.users.application.dtos.response.UserDTO;
import com.joel.users.application.mapper.UserMapper;
import com.joel.users.application.ports.usecases.users.CreateUserUseCase;
import com.joel.users.domain.entities.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final CreateUserUseCase createUserUseCase;
    private final UserMapper mapper;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO registerUser(@RequestBody @Valid CreateUserRequestDTO createUserRequestDTO) {
        CreateUserCommand createCommand = mapper.toCreateCommandFromDto(createUserRequestDTO);
        User user = createUserUseCase.execute(createCommand);
        return mapper.toDtoFromDomain(user);
    }

}
