package com.crud.mongo.CrudApplication.service.impl;


import com.crud.mongo.CrudApplication.dto.EmployeeRequestDto;
import com.crud.mongo.CrudApplication.dto.EmployeeResponseDto;
import com.crud.mongo.CrudApplication.exception.ResourceNotFoundException;
import com.crud.mongo.CrudApplication.model.Employee;
import com.crud.mongo.CrudApplication.repository.EmployeeRepository;
import com.crud.mongo.CrudApplication.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log =
            LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository repository;

    // ✅ EXPLICIT CONSTRUCTOR (NO Lombok)
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeResponseDto create(EmployeeRequestDto request) {
        log.info("START create");

        Employee employee = new Employee();
        employee.setName(request.name());
        employee.setEmail(request.email());
        employee.setDepartment(request.department());
        employee.setSalary(request.salary());

        Employee saved = repository.save(employee);

        log.info("END create | id={}", saved.getId());
        return mapToResponse(saved);
    }

    @Override
    public EmployeeResponseDto getById(String id) {
        log.info("START getById | id={}", id);

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found: " + id));

        log.info("END getById | id={}", id);
        return mapToResponse(employee);
    }

    @Override
    public List<EmployeeResponseDto> getAll() {
        log.info("START getAll");

        List<EmployeeResponseDto> list = repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        log.info("END getAll | count={}", list.size());
        return list;
    }

    @Override
    public EmployeeResponseDto update(String id, EmployeeRequestDto request) {
        log.info("START update | id={}", id);

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found: " + id));

        employee.setName(request.name());
        employee.setEmail(request.email());
        employee.setDepartment(request.department());
        employee.setSalary(request.salary());

        Employee updated = repository.save(employee);

        log.info("END update | id={}", id);
        return mapToResponse(updated);
    }

    @Override
    public void delete(String id) {
        log.info("START delete | id={}", id);

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found: " + id);
        }

        repository.deleteById(id);

        log.info("END delete | id={}", id);
    }

    private EmployeeResponseDto mapToResponse(Employee employee) {
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getSalary()
        );
    }
}
