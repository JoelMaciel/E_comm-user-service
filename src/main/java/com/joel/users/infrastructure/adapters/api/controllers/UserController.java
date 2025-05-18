package com.joel.users.infrastructure.adapters.api.controllers;

import com.joel.users.application.commands.UserUpdateCommand;
import com.joel.users.application.dtos.request.UserUpdateRequestDTO;
import com.joel.users.application.dtos.response.PaginationDTO;
import com.joel.users.application.dtos.response.UserDTO;
import com.joel.users.application.mapper.UserMapper;
import com.joel.users.application.ports.usecases.users.DeleteUserUseCase;
import com.joel.users.application.ports.usecases.users.ListUserUseCase;
import com.joel.users.application.ports.usecases.users.ShowUserUseCase;
import com.joel.users.application.ports.usecases.users.UpdateUserUseCase;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.pagination.Pagination;
import jakarta.validation.Valid;
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
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
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

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID userId) {
        deleteUserUseCase.delete(userId);
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@PathVariable UUID userId, @RequestBody @Valid UserUpdateRequestDTO updateRequestDTO) {

        UserUpdateCommand userCommand = mapper.toUpdateCommandFromDto(userId, updateRequestDTO);

        User user = updateUserUseCase.execute(userCommand);
        return mapper.toDtoFromDomain(user);
    }

}
