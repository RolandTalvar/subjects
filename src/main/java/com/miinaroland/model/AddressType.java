package com.miinaroland.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by miinaroland on 02/06/16.
 */
@Entity
public class AddressType {

    @Id
    private long addressType;
    private String typeName;

    public long getAddressType() {
        return addressType;
    }

    public void setAddressType(long addressType) {
        this.addressType = addressType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
