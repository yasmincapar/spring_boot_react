package com.spring_boot_react_app.spring_react.repository;

import com.spring_boot_react_app.spring_react.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repository Layer:
//Interacts with the Entity to perform database operations.

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

}
