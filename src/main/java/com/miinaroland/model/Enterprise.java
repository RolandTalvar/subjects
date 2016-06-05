package com.miinaroland.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rolandtalvar on 02/06/16.
 */
@Entity
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enterprise_id")
    @SequenceGenerator(name="enterprise_id", sequenceName="enterprise_id", allocationSize=1)
    private long enterprise;
    private String name;
    private String fullName;
    private Long createdBy;
    private Long updatedBy;
    private Date created;
    private Date updated;

    public long getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(long enterprise) {
        this.enterprise = enterprise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
