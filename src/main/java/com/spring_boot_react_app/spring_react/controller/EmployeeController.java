package com.spring_boot_react_app.spring_react.controller;


import com.spring_boot_react_app.spring_react.model.Employee;
import com.spring_boot_react_app.spring_react.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controller Layer:
//
//Handles HTTP requests and responses.
//Uses DTOs to interact with clients (both for input and output).
//Calls the service layer with DTOs, and receives DTOs to return to the client.

//Now we have annotated our class with @REStController now it becomes possible for this class to handle HTTP request and responses
@RestController
@AllArgsConstructor
//this allows you to define a base url
@RequestMapping("/employees")
public class EmployeeController {
    //we need to inject the dependencies
    private EmployeeService service;

    /**
     *
     * @param employee
     * @return
     */
    @PostMapping("/registerEmployee")
    public long registerUser(@RequestBody Employee employee) {
        return service.registerTheEmployee(employee);
    }

    /**
     *Using the method below we will get employee
     * we pass an id then we expect the user to be returned
     * from here should go to service then we from service should go to repository and fetch the user with that specific id
     * @PathVariable annotation is used to bind a method parameter to a URI template variable. This allows you to extract values from the URI and use them as parameters in your controller methods
     * You can use the @PathVariable annotation to capture the employee ID from the URL.
     * @param
     * @return
     */
    @GetMapping("/getAllEmployee/{id}")
    public Employee getSingleEmployeeById(@PathVariable Long id){
        Employee employee = service.getEmployeeById(id);
        return employee;
    }

    //get all emplyees findAll method from service we return Employee back
    //as we are getting something from the database no need to pass anything to the method
    @GetMapping("/getAllEmployees")
    public List<Employee> returnAllEmployees(){
        return service.getAllEmployees();
    }
}
