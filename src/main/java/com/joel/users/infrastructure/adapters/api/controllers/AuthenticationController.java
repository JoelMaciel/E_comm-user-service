package com.joel.users.infrastructure.adapters.api.controllers;

import com.joel.users.application.dtos.request.UserRequestDTO;
import com.joel.users.application.dtos.response.UserDTO;
import com.joel.users.application.ports.CreateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO registerUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return createUserUseCase.createUser(userRequestDTO);
    }

}
