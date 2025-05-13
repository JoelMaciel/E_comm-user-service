package com.joel.users.domain.entities;

import com.joel.users.domain.enums.UserStatus;
import com.joel.users.domain.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @EqualsAndHashCode.Include
    private UUID id;

    private String username;
    private String email;
    private String password;
    private String fullName;

    private UserType userType;
    private UserStatus userStatus;

    private String phoneNumber;
    private String cpf;
    private String imageUrl;

    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
