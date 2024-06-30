package com.spring_boot_react_app.spring_react.controller;


import com.spring_boot_react_app.spring_react.model.Employee;
import com.spring_boot_react_app.spring_react.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
