package com.miinaroland.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by miinaroland on 02/06/16.
 */
@Entity
public class EmployeeRole {

    @Id
    private long employeeRole;
    private Long employeeFk;
    private Long employeeRoleTypeFk;
    private String active;

    public long getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(long employeeRole) {
        this.employeeRole = employeeRole;
    }

    public Long getEmployeeFk() {
        return employeeFk;
    }

    public void setEmployeeFk(Long employeeFk) {
        this.employeeFk = employeeFk;
    }

    public Long getEmployeeRoleTypeFk() {
        return employeeRoleTypeFk;
    }

    public void setEmployeeRoleTypeFk(Long employeeRoleTypeFk) {
        this.employeeRoleTypeFk = employeeRoleTypeFk;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
