package com.joel.users.application.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdatePasswordDTO {

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    @NotBlank
    private String oldPassword;
}
