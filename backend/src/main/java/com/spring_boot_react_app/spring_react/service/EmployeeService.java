package com.spring_boot_react_app.spring_react.service;

import com.spring_boot_react_app.spring_react.entity.EmployeeEntity;
import com.spring_boot_react_app.spring_react.exception.EmployeeNotFoundException;
import com.spring_boot_react_app.spring_react.model.Employee;
import com.spring_boot_react_app.spring_react.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
//Calls the repository layer to get entities and processes them to create DTOs for the controller layer.
public class EmployeeService {
    private final EmployeeRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

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

    public Employee getEmployeeById(Long id) {
        logger.info("Fetching employee with ID: {}", id);
        //we assign entity the data that is coming from the repository
        //EmployeeEntity entity = repository.getReferenceById(id);
        //EmployeeEntity entity = repository.getReferenceById(id);
        EmployeeEntity entity = repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Employee not found with ID: {}", id);
                    return new EmployeeNotFoundException("The employee doesn't exist with this given ID: " + id);
                });
        logger.info("Employee found: {}", entity);

        //Convert the entity to a model to send the employee data back to the controller.
        Employee employee = Employee.builder().firstName(entity.getFirstName()).lastName(entity.getLastName()).email(entity.getEmail()).build();
        logger.info("Returning employee: {}", employee);
        return employee;
    }

    public List<Employee> getAllEmployees() {
        //I have to convert the entity to a model then send it back to the controller
        //store the employees in entity
        List<EmployeeEntity> employeeEntities = repository.findAll();
        List<Employee> employee = new ArrayList<>();
        //for every employee in the employeeEntities list
        for (EmployeeEntity x : employeeEntities) {
            //now this is where we convert the employees from entity to model
            Employee allemployees = Employee.builder().firstName(x.getFirstName()).lastName(x.getLastName()).email(x.getEmail()).build();
            //now add all these employees to the employee list
            employee.add(allemployees);}
        //Convert the entity to a model to send the employee data back to the controller.
        return employee;
    }

    public void deleteEmployeeById(Long id) {

        if(!repository.existsById(id)){
            logger.info("Employee with this id not found {}",id);
            throw new EmployeeNotFoundException("The employee doesn't exist with this given ID: " + id);
        }
        repository.deleteById(id);

    }

    public Employee updateTheEmployeeById(Long id, Employee employee) {
        EmployeeEntity entity = repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Employee not found with ID: {}", id);
                    return new EmployeeNotFoundException("The employee doesn't exist with this given ID: " + id);
                });

        // Update the entity fields with the new values
        entity.setFirstName(employee.getFirstName());
        entity.setLastName(employee.getLastName());
        entity.setEmail(employee.getEmail());

        // Save the updated entity back to the repository
        EmployeeEntity updatedEntity = repository.save(entity);
        logger.info("Updated employee: {}", updatedEntity);

        // Convert the updated entity back to a model to return
        Employee updatedEmployee = Employee.builder()
                .firstName(updatedEntity.getFirstName())
                .lastName(updatedEntity.getLastName())
                .email(updatedEntity.getEmail())
                .build();

        return updatedEmployee;
    }

}
