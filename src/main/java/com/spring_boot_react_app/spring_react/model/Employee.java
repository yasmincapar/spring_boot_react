package com.spring_boot_react_app.spring_react.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO Data transfer object, used to transfer data between different parts of a software application.
//
//The DTO is used to transfer data between different layers of the application, particularly between the controller and service layers. DTOs are not tied to the database schema
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
//

