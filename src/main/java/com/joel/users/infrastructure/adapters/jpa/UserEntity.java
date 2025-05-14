package com.joel.users.infrastructure.adapters.jpa;

import com.joel.users.domain.enums.UserStatus;
import com.joel.users.domain.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    private String username;
    private String email;
    private String password;
    private String fullName;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    private String phoneNumber;
    private String cpf;
    private String imageUrl;

    @CreationTimestamp
    private OffsetDateTime creationDate;

    @UpdateTimestamp
    private OffsetDateTime updateDate;

}