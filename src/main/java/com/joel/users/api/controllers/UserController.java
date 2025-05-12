package com.joel.users.api.controllers;

import com.joel.users.domain.dtos.response.UserDTO;
import com.joel.users.domain.usecases.ListUserUseCase;
import com.joel.users.domain.usecases.ShowUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final ShowUserUseCase showUserUseCase;
    private final ListUserUseCase listUserUseCase;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getOne(@PathVariable UUID userId) {
        return showUserUseCase.findById(userId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserDTO> getAll(@PageableDefault(
            page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC
    ) Pageable pageable) {
        return listUserUseCase.findAll(pageable);
    }
}
