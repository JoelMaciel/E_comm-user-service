package com.joel.users.application.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateRequestDTO {

    @NotBlank
    @Size(min = 6, max = 50)
    private String username;


    @NotBlank
    @Size(min = 10, max = 150)
    private String fullName;

    @NotBlank
    @Size(min = 10, max = 15)
    private String phoneNumber;

}
