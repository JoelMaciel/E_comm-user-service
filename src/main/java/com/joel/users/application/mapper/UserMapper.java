package com.joel.users.application.mapper;

import com.joel.users.application.commands.CreateUserCommand;
import com.joel.users.application.commands.EmployeeCommand;
import com.joel.users.application.commands.UpdatePasswordCommand;
import com.joel.users.application.commands.UpdateUserCommand;
import com.joel.users.application.dtos.request.CreateUserRequestDTO;
import com.joel.users.application.dtos.request.EmployeeRequestDTO;
import com.joel.users.application.dtos.request.UpdatePasswordDTO;
import com.joel.users.application.dtos.request.UpdateUserRequestDTO;
import com.joel.users.application.dtos.response.PaginationDTO;
import com.joel.users.application.dtos.response.UserDTO;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.enums.UserStatus;
import com.joel.users.domain.enums.UserType;
import com.joel.users.domain.pagination.Pagination;
import com.joel.users.infrastructure.adapters.jpa.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public PaginationDTO<UserDTO> toPaginationDto(Pagination<User> domainPagination) {
        List<UserDTO> content = domainPagination.content().stream()
                .map(this::toDtoFromDomain)
                .toList();

        return new PaginationDTO<>(
                content,
                domainPagination.pageNumber(),
                domainPagination.size(),
                domainPagination.totalElements()
        );
    }


    public User toDomainFromEntity(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .fullName(entity.getFullName())
                .userType(entity.getUserType())
                .userStatus(entity.getUserStatus())
                .phoneNumber(entity.getPhoneNumber())
                .cpf(entity.getCpf())
                .imageUrl(entity.getImageUrl())
                .creationDate(entity.getCreationDate())
                .updateDate(entity.getUpdateDate())
                .build();
    }


    public UserEntity toEntityFromDomain(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .userType(user.getUserType())
                .userStatus(user.getUserStatus())
                .phoneNumber(user.getPhoneNumber())
                .cpf(user.getCpf())
                .imageUrl(user.getImageUrl())
                .creationDate(user.getCreationDate())
                .updateDate(user.getUpdateDate())
                .build();
    }


    public UserDTO toDtoFromDomain(User user) {
        return UserDTO.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .cpf(user.getCpf())
                .phoneNumber(user.getPhoneNumber())
                .userType(user.getUserType().name())
                .userStatus(user.getUserStatus().name())
                .imageUrl(user.getImageUrl())
                .creationDate(user.getCreationDate())
                .updateDate(user.getUpdateDate())
                .build();
    }

    public UpdateUserCommand toUpdateCommandFromDto(UUID userId, UpdateUserRequestDTO dto) {
        return UpdateUserCommand.builder()
                .userId(userId)
                .username(dto.getUsername())
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public EmployeeCommand toEmployeeCommandFromDto(EmployeeRequestDTO dto) {
        return EmployeeCommand.builder()
                .userId(dto.getUserId())
                .build();
    }

    public CreateUserCommand toCreateCommandFromDto(CreateUserRequestDTO dto) {
        return CreateUserCommand.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .cpf(dto.getCpf())
                .build();
    }

    public UpdatePasswordCommand toUpdatePasswordFromDomain(UUID userId, UpdatePasswordDTO dto) {
        return UpdatePasswordCommand.builder()
                .userId(userId)
                .password(dto.getPassword())
                .oldPassword(dto.getOldPassword())
                .build();
    }

    public User toDomainFromUpdatePasswordCommand(UpdatePasswordCommand updatePasswordCommand, User user) {
        return user.toBuilder()
                .password(passwordEncoder.encode(updatePasswordCommand.password()))
                .updateDate(OffsetDateTime.now())
                .build();
    }

    public User toDomainFromCommand(CreateUserCommand createUserCommand) {
        return User.builder()
                .username(createUserCommand.username())
                .email(createUserCommand.email())
                .cpf(createUserCommand.cpf())
                .userStatus(UserStatus.ACTIVE)
                .userType(UserType.USER)
                .fullName(createUserCommand.fullName())
                .phoneNumber(createUserCommand.phoneNumber())
                .password(passwordEncoder.encode(createUserCommand.password()))
                .creationDate(OffsetDateTime.now())
                .updateDate(OffsetDateTime.now())
                .build();
    }


    public User toDomainFromCommand(UpdateUserCommand updateUserCommand, User existingUser) {
        return existingUser.toBuilder()
                .username(updateUserCommand.username())
                .fullName(updateUserCommand.fullName())
                .phoneNumber(updateUserCommand.phoneNumber())
                .updateDate(OffsetDateTime.now())
                .build();

    }

    public User toUpdateEmployeeDomainFromCommand(User user) {
        return user.toBuilder()
                .userType(UserType.EMPLOYEE)
                .updateDate(OffsetDateTime.now())
                .build();
    }

    public void updateEntityFromDomain(UserEntity entity, User user) {
        entity.setUsername(user.getUsername());
        entity.setFullName(user.getFullName());
        entity.setPhoneNumber(user.getPhoneNumber());
        entity.setUpdateDate(OffsetDateTime.now());
    }

}
