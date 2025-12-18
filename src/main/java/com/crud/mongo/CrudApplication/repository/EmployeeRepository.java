package com.crud.mongo.CrudApplication.repository;

import com.crud.mongo.CrudApplication.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}

