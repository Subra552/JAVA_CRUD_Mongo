package com.crud.mongo.CrudApplication.controller;


import com.crud.mongo.CrudApplication.dto.EmployeeRequestDto;
import com.crud.mongo.CrudApplication.dto.EmployeeResponseDto;
import com.crud.mongo.CrudApplication.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public EmployeeResponseDto create(@Valid @RequestBody EmployeeRequestDto request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping
    public List<EmployeeResponseDto> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public EmployeeResponseDto update(
            @PathVariable String id,
            @Valid @RequestBody EmployeeRequestDto request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}

