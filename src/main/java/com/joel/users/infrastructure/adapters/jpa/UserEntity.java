package com.joel.users.infrastructure.adapters.jpa;

import com.joel.users.domain.entities.User;
import com.joel.users.domain.enums.UserStatus;
import com.joel.users.domain.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    public User toDomain() {
        return User.builder()
                .id(this.id)
                .username(this.username)
                .email(this.email)
                .password(this.password)
                .fullName(this.fullName)
                .userType(this.userType)
                .userStatus(this.userStatus)
                .phoneNumber(this.phoneNumber)
                .cpf(this.cpf)
                .imageUrl(this.imageUrl)
                .creationDate(this.creationDate)
                .updateDate(this.updateDate)
                .build();
    }

    public static UserEntity fromDomain(User user) {
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
}