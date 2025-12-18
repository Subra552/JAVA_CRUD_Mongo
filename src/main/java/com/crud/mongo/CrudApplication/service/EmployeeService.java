package com.crud.mongo.CrudApplication.service;


import com.crud.mongo.CrudApplication.dto.EmployeeRequestDto;
import com.crud.mongo.CrudApplication.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDto create(EmployeeRequestDto request);

    EmployeeResponseDto getById(String id);

    List<EmployeeResponseDto> getAll();

    EmployeeResponseDto update(String id, EmployeeRequestDto request);

    void delete(String id);
}

