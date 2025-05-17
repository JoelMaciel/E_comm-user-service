package com.joel.users.application.mapper;

import com.joel.users.application.dtos.request.UserRequestDTO;
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

    public User toDomainFromDto(UserRequestDTO userRequestDTO) {
        return User.builder()
                .username(userRequestDTO.getUsername())
                .email(userRequestDTO.getEmail())
                .cpf(userRequestDTO.getCpf())
                .userStatus(UserStatus.ACTIVE)
                .userType(UserType.USER)
                .fullName(userRequestDTO.getFullName())
                .phoneNumber(userRequestDTO.getPhoneNumber())
                .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                .creationDate(OffsetDateTime.now())
                .updateDate(OffsetDateTime.now())
                .build();
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

    public UserDTO toDto(User user) {
        return UserDTO.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .userStatus(user.getUserStatus().toString())
                .userType(user.getUserType().toString())
                .cpf(user.getCpf())
                .imageUrl(user.getImageUrl())
                .creationDate(user.getCreationDate())
                .updateDate(user.getUpdateDate())
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
}
