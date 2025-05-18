package com.joel.users.application.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmployeeRequestDTO {

    @NotNull
    private UUID userId;
}
