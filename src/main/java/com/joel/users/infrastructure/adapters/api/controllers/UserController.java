package com.joel.users.infrastructure.adapters.api.controllers;

import com.joel.users.application.commands.UpdatePasswordCommand;
import com.joel.users.application.commands.UpdateUserCommand;
import com.joel.users.application.dtos.request.UpdatePasswordDTO;
import com.joel.users.application.dtos.request.UpdateUserRequestDTO;
import com.joel.users.application.dtos.response.PaginationDTO;
import com.joel.users.application.dtos.response.UserDTO;
import com.joel.users.application.mapper.UserMapper;
import com.joel.users.application.ports.usecases.users.*;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.pagination.Pagination;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    public static final String MSG_UPDATE_PASSWORD = "Password updated successfully.";

    private final ShowUserUseCase showUserUseCase;
    private final ListUserUseCase listUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final UserMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PaginationDTO<UserDTO> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pagination<User> domainPagination = listUserUseCase.execute(page, size);
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
        deleteUserUseCase.execute(userId);
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@PathVariable UUID userId, @RequestBody @Valid UpdateUserRequestDTO updateRequestDTO) {

        UpdateUserCommand userCommand = mapper.toUpdateCommandFromDto(userId, updateRequestDTO);

        User user = updateUserUseCase.execute(userCommand);
        return mapper.toDtoFromDomain(user);
    }

    @PatchMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updatePassword(@PathVariable UUID userId, @RequestBody @Valid UpdatePasswordDTO updatePasswordDTO) {
        UpdatePasswordCommand userCommand = mapper.toUpdatePasswordFromDomain(userId, updatePasswordDTO);
        updatePasswordUseCase.execute(userCommand);
        return ResponseEntity.ok(MSG_UPDATE_PASSWORD);
    }

}
