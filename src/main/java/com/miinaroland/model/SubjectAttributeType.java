package com.miinaroland.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by miinaroland on 02/06/16.
 */
@Entity
public class SubjectAttributeType {

    @Id
    private long subjectAttributeType;
    private Long subjectTypeFk;
    private String typeName;
    private int dataType;
    private Long orderby;
    private String required;
    private String multipleAttributes;
    private String createdByDefault;

    public long getSubjectAttributeType() {
        return subjectAttributeType;
    }

    public void setSubjectAttributeType(long subjectAttributeType) {
        this.subjectAttributeType = subjectAttributeType;
    }

    public Long getSubjectTypeFk() {
        return subjectTypeFk;
    }

    public void setSubjectTypeFk(Long subjectTypeFk) {
        this.subjectTypeFk = subjectTypeFk;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public Long getOrderby() {
        return orderby;
    }

    public void setOrderby(Long orderby) {
        this.orderby = orderby;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getMultipleAttributes() {
        return multipleAttributes;
    }

    public void setMultipleAttributes(String multipleAttributes) {
        this.multipleAttributes = multipleAttributes;
    }

    public String getCreatedByDefault() {
        return createdByDefault;
    }

    public void setCreatedByDefault(String createdByDefault) {
        this.createdByDefault = createdByDefault;
    }
}
