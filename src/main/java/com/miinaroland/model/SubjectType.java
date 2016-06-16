package com.miinaroland.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by miinaroland on 02/06/16.
 */
@Entity
public class SubjectType {

    @Id
    private long subjectType;
    private String typeName;

    public long getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(long subjectType) {
        this.subjectType = subjectType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
