package com.joel.users.domain.mapper;

import com.joel.users.domain.dtos.request.UserRequestDTO;
import com.joel.users.domain.dtos.response.UserDTO;
import com.joel.users.domain.entities.User;
import com.joel.users.domain.enums.UserStatus;
import com.joel.users.domain.enums.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public Page<UserDTO> toDTOPage(Page<User> users) {
        return users.map(this::toDTO);
    }

    public UserDTO toDTO(User user) {
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
                .updateDate((user.getUpdateDate()))
                .build();
    }

    public User toEntity(UserRequestDTO userRequestDTO) {
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
}
