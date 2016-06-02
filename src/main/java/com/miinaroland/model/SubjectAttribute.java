package com.miinaroland.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by rolandtalvar on 02/06/16.
 */
@Entity
public class SubjectAttribute {

    @Id
    private long subjectAttribute;
    private Long subjectFk;
    private Long subjectTypeFk;
    private Long subjectAttributeTypeFk;
    private Long orderby;
    private String valueText;
    private Long valueNumber;
    private Date valueDate;
    private int dataType;

    public long getSubjectAttribute() {
        return subjectAttribute;
    }

    public void setSubjectAttribute(long subjectAttribute) {
        this.subjectAttribute = subjectAttribute;
    }

    public Long getSubjectFk() {
        return subjectFk;
    }

    public void setSubjectFk(Long subjectFk) {
        this.subjectFk = subjectFk;
    }

    public Long getSubjectTypeFk() {
        return subjectTypeFk;
    }

    public void setSubjectTypeFk(Long subjectTypeFk) {
        this.subjectTypeFk = subjectTypeFk;
    }

    public Long getSubjectAttributeTypeFk() {
        return subjectAttributeTypeFk;
    }

    public void setSubjectAttributeTypeFk(Long subjectAttributeTypeFk) {
        this.subjectAttributeTypeFk = subjectAttributeTypeFk;
    }

    public Long getOrderby() {
        return orderby;
    }

    public void setOrderby(Long orderby) {
        this.orderby = orderby;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public Long getValueNumber() {
        return valueNumber;
    }

    public void setValueNumber(Long valueNumber) {
        this.valueNumber = valueNumber;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }
}
