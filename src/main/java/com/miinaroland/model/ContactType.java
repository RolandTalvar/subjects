package com.miinaroland.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by miinaroland on 02/06/16.
 */
@Entity
public class ContactType {

    @Id
    private long contactType;
    private String typeName;

    public long getContactType() {
        return contactType;
    }

    public void setContactType(long contactType) {
        this.contactType = contactType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
