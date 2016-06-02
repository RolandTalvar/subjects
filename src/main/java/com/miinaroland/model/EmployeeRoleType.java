package com.miinaroland.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by rolandtalvar on 02/06/16.
 */
@Entity
public class EmployeeRoleType {

    @Id
    private long employeeRoleType;
    private String typeName;

    public long getEmployeeRoleType() {
        return employeeRoleType;
    }

    public void setEmployeeRoleType(long employeeRoleType) {
        this.employeeRoleType = employeeRoleType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
