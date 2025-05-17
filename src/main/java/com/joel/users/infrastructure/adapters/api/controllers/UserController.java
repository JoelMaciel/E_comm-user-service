package com.joel.users.infrastructure.adapters.api.controllers;

import com.joel.users.application.dtos.response.PaginationDTO;
import com.joel.users.application.dtos.response.UserDTO;
import com.joel.users.application.mapper.UserMapper;
import com.joel.users.application.ports.usecases.users.ListUserUseCase;
import com.joel.users.application.ports.usecases.users.ShowUserUseCase;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.pagination.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final ShowUserUseCase showUserUseCase;
    private final ListUserUseCase listUserUseCase;
    private final UserMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PaginationDTO<UserDTO> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pagination<User> domainPagination = listUserUseCase.findAll(page, size);
        return mapper.toPaginationDto(domainPagination);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getOne(@PathVariable UUID userId) {
        return mapper.toDtoFromDomain(showUserUseCase.findById(userId));
    }

}
