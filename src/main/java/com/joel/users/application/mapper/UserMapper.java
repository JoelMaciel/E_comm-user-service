package com.joel.users.application.mapper;

import com.joel.users.application.dtos.request.UserRequestDTO;
import com.joel.users.application.dtos.response.UserDTO;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.enums.UserStatus;
import com.joel.users.domain.enums.UserType;
import com.joel.users.infrastructure.adapters.jpa.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    // DTO -> Domain
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
                .build();
    }

    // Entity (JPA) -> Domain
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

    // Domain -> DTO
    public UserDTO toDto(User user) {
        return UserDTO.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .userStatus(user.getUserStatus().toString())
                .userType(user.getUserType().toString())
                .cpf(user.getCpf())
                .phoneNumber(user.getPhoneNumber())
                .imageUrl(user.getImageUrl())
                .creationDate(user.getCreationDate())
                .updateDate(user.getUpdateDate())
                .build();
    }

    // Domain -> Entity (JPA)
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

    // Page<User> -> Page<UserDTO>
    public Page<UserDTO> toDtoPage(Page<User> users) {
        return users.map(this::toDto);
    }
}
