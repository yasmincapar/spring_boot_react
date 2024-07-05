package com.spring_boot_react_app.spring_react.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Entity annotaion in java is used to specify that a class is an entity and is mapped to a table in a database.
//It is part of the Java Persistence API which is a standard API for ORM in hava applications.
//Repository Layer:
//Interacts with the Entity to perform database operations.
//Entities: Are tightly coupled with the database schema and are used by the repository layer to interact with the database.
//Annotated with @Entity, @Table, and other JPA annotations.
//Contains fields that correspond to the columns in the database table.
//When @Entity annotation is used tells the JPA that this class should be mapped to a database table.
//Each entity must have a primary key so a unique identifier so no 2 records(rows) have the same primary key value so in this case we have Long so each record is identified with 1, 2 , 3 etc. .
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "employees")//specify table name
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    //name = "email_id" specifies the name of the column in the database table.
    @Column(name = "email_id", nullable = false, unique = true)
    private String email;
}
