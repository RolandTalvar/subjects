package com.miinaroland.repository;

import com.miinaroland.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by miinaroland on 02/06/16.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmployee(long employee);
    Employee findByPersonFk(long personFk);
}
