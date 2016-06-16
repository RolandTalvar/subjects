package com.miinaroland.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by miinaroland on 02/06/16.
 */
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id")
    @SequenceGenerator(name="person_id", sequenceName="person_id", allocationSize=1)
    private long person;

    @Size(min=2, max=30)
    private String firstName;

    @Size(min=2, max=30)
    private String lastName;

    @Pattern(regexp="^[3-6][0-9][0-9][0-1][0-9][0-3][0-9][0-9][0-9][0-9][0-9]$",message="Invalid identity code format")
    private String identityCode;

    private java.sql.Date birthDate;
    private Long createdBy;
    private Long updatedBy;
    private Date created;
    private Date updated;

    public long getPerson() {
        return person;
    }

    public void setPerson(long person) {
        this.person = person;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public java.sql.Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(java.sql.Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
