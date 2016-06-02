package com.miinaroland.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by rolandtalvar on 02/06/16.
 */
@Entity
public class StructUnit {

    @Id
    private long structUnit;
    private Long enterpriseFk;
    private Long level;
    private String name;
    private Long upperUnitFk;

    public long getStructUnit() {
        return structUnit;
    }

    public void setStructUnit(long structUnit) {
        this.structUnit = structUnit;
    }

    public Long getEnterpriseFk() {
        return enterpriseFk;
    }

    public void setEnterpriseFk(Long enterpriseFk) {
        this.enterpriseFk = enterpriseFk;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUpperUnitFk() {
        return upperUnitFk;
    }

    public void setUpperUnitFk(Long upperUnitFk) {
        this.upperUnitFk = upperUnitFk;
    }
}
