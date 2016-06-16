package com.miinaroland.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by miinaroland on 02/06/16.
 */
@Entity
public class EnterprisePersonRelation {

    @Id
    private long enterprisePersonRelation;
    private Long entPerRelationTypeFk;
    private Long personFk;
    private Long enterpriseFk;

    public long getEnterprisePersonRelation() {
        return enterprisePersonRelation;
    }

    public void setEnterprisePersonRelation(long enterprisePersonRelation) {
        this.enterprisePersonRelation = enterprisePersonRelation;
    }

    public Long getEntPerRelationTypeFk() {
        return entPerRelationTypeFk;
    }

    public void setEntPerRelationTypeFk(Long entPerRelationTypeFk) {
        this.entPerRelationTypeFk = entPerRelationTypeFk;
    }

    public Long getPersonFk() {
        return personFk;
    }

    public void setPersonFk(Long personFk) {
        this.personFk = personFk;
    }

    public Long getEnterpriseFk() {
        return enterpriseFk;
    }

    public void setEnterpriseFk(Long enterpriseFk) {
        this.enterpriseFk = enterpriseFk;
    }
}
