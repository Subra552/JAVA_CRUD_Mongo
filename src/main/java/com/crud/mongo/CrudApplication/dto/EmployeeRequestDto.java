package com.crud.mongo.CrudApplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeRequestDto(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String department,
        @NotNull Double salary
) {
}
