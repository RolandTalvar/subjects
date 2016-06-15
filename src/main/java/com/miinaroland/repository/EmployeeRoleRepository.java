package com.miinaroland.repository;

import com.miinaroland.model.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by rolandtalvar on 02/06/16.
 */
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Long> {

    List<EmployeeRole> findByEmployeeFk(long employeeFk);
}
