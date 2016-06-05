package com.miinaroland.model;

import javax.persistence.*;

/**
 * Created by rolandtalvar on 02/06/16.
 */

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id")
    @SequenceGenerator(name="employee_id", sequenceName="employee_id", allocationSize=1)
    private long employee;
    private Long structUnitFk;
    private Long enterpriseFk;
    private Long personFk;
    private String active;

    public long getEmployee() {
        return employee;
    }

    public void setEmployee(long employee) {
        this.employee = employee;
    }

    public Long getStructUnitFk() {
        return structUnitFk;
    }

    public void setStructUnitFk(Long structUnitFk) {
        this.structUnitFk = structUnitFk;
    }

    public Long getEnterpriseFk() {
        return enterpriseFk;
    }

    public void setEnterpriseFk(Long enterpriseFk) {
        this.enterpriseFk = enterpriseFk;
    }

    public Long getPersonFk() {
        return personFk;
    }

    public void setPersonFk(Long personFk) {
        this.personFk = personFk;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
