package com.crud.mongo.CrudApplication.dto;


public record EmployeeResponseDto(
        String id,
        String name,
        String email,
        String department,
        Double salary
) {
}

