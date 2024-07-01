package com.spring_boot_react_app.spring_react.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * This specifies that whenever ResourceNotFoundException is thrown, the response should have an HTTP status code of 404 Not Found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message){
        super(message);

    }
}
