package com.miinaroland.model;

import javax.persistence.*;

/**
 * Created by miinaroland on 02/06/16.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id")
    @SequenceGenerator(name="customer_id", sequenceName="customer_id", allocationSize=1)
    private long customer;
    private Long subjectTypeFk;
    private Long subjectFk;

    public long getCustomer() {
        return customer;
    }

    public void setCustomer(long customer) {
        this.customer = customer;
    }

    public Long getSubjectTypeFk() {
        return subjectTypeFk;
    }

    public void setSubjectTypeFk(Long subjectTypeFk) {
        this.subjectTypeFk = subjectTypeFk;
    }

    public Long getSubjectFk() {
        return subjectFk;
    }

    public void setSubjectFk(Long subjectFk) {
        this.subjectFk = subjectFk;
    }
}
