package com.spring_boot_react_app.spring_react.controller;


import com.spring_boot_react_app.spring_react.exception.EmployeeNotFoundException;
import com.spring_boot_react_app.spring_react.model.Employee;
import com.spring_boot_react_app.spring_react.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    //Used to create a logger instance to understand the internal state of the development process. Prints to the console.
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);


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
     * A client makes an HTTP GET request to the endpoint /getAllEmployee/{id}.
     * @param
     * @return
     */
    @GetMapping("/getAllEmployee/{id}")
    public Employee getSingleEmployeeById(@PathVariable("id") Long id){
        Employee employee = service.getEmployeeById(id);
        return employee;
    }


    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(EmployeeNotFoundException ex) {
        logger.error("Exception caught: ", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //get all emplyees findAll method from service we return Employee back
    //as we are getting something from the database no need to pass anything to the method
    @GetMapping("/getAllEmployees")
    public List<Employee> returnAllEmployees(){
        return service.getAllEmployees();
    }

    //Update


    //Delete return the id in which the user has been deleted
    //we enter the id of which the user we want to delete
    //we give the id and we can return all the users left or the number of users left
    @DeleteMapping("/deletEmployee/{id}")
    public String deleteTheEmployee(@PathVariable("id") Long id){
        service.deleteEmployeeById(id);
        return "The Employee has been deleted successfully";
    }


}
