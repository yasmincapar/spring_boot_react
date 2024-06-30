package com.spring_boot_react_app.spring_react.service;

import com.spring_boot_react_app.spring_react.entity.EmployeeEntity;
import com.spring_boot_react_app.spring_react.model.Employee;
import com.spring_boot_react_app.spring_react.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
//Calls the repository layer to get entities and processes them to create DTOs for the controller layer.
public class EmployeeService {
    private final EmployeeRepository repository;

    /**
     * The registerTheEmployee method converts an Employee DTO to an EmployeeEntity, saves it to the database using the save method provided by the JPA repository, and returns the generated ID.
     * @param employee
     * @return
     */
    public long registerTheEmployee(Employee employee) {
        EmployeeEntity employeeEntity =
                EmployeeEntity.builder().firstName(employee.getFirstName()).lastName(employee.getLastName()).email(employee.getEmail()).build();
                employeeEntity = repository.save(employeeEntity);
                //save method is a built in method provided by the JPA repository used to save an entity to a database.
                return employeeEntity.getId();


    }
}
