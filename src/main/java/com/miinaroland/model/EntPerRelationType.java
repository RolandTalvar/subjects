package com.miinaroland.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by miinaroland on 02/06/16.
 */
@Entity
public class EntPerRelationType {

    @Id
    private long entPerRelationType;
    private String typeName;

    public long getEntPerRelationType() {
        return entPerRelationType;
    }

    public void setEntPerRelationType(long entPerRelationType) {
        this.entPerRelationType = entPerRelationType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
