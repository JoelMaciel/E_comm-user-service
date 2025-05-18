package com.joel.users.application.mapper;

import com.joel.users.application.commands.UserCreateCommand;
import com.joel.users.application.commands.UserUpdateCommand;
import com.joel.users.application.dtos.request.UserRequestDTO;
import com.joel.users.application.dtos.request.UserUpdateRequestDTO;
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


    public UserEntity toEntityFromDomain(User domain) {
        return UserEntity.builder()
                .id(domain.getId())
                .username(domain.getUsername())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .fullName(domain.getFullName())
                .userType(domain.getUserType())
                .userStatus(domain.getUserStatus())
                .phoneNumber(domain.getPhoneNumber())
                .cpf(domain.getCpf())
                .imageUrl(domain.getImageUrl())
                .creationDate(domain.getCreationDate())
                .updateDate(domain.getUpdateDate())
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

    public UserUpdateCommand toUpdateCommandFromDto(UUID userId, UserUpdateRequestDTO dto) {
        return new UserUpdateCommand(
                userId,
                dto.getUsername(),
                dto.getFullName(),
                dto.getPhoneNumber()
        );
    }

    public UserCreateCommand toCreateCommandFromDto(UserRequestDTO dto) {
        return new UserCreateCommand(
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getFullName(),
                dto.getPhoneNumber(),
                dto.getCpf()
        );
    }


    public User toDomainFromCommand(UserCreateCommand command) {
        return User.builder()
                .username(command.username())
                .email(command.email())
                .cpf(command.cpf())
                .userStatus(UserStatus.ACTIVE)
                .userType(UserType.USER)
                .fullName(command.fullName())
                .phoneNumber(command.phoneNumber())
                .password(passwordEncoder.encode(command.password()))
                .creationDate(OffsetDateTime.now())
                .updateDate(OffsetDateTime.now())
                .build();
    }


    public User toDomainFromCommand(UserUpdateCommand userUpdateCommand, User existingUser) {
        return existingUser.toBuilder()
                .username(userUpdateCommand.username())
                .fullName(userUpdateCommand.fullName())
                .phoneNumber(userUpdateCommand.phoneNumber())
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
