package com.miinaroland.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by miinaroland on 02/06/16.
 */
@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_account_id")
    @SequenceGenerator(name="user_account_id", sequenceName="user_account_id", allocationSize=1)
    private long userAccount;
    private Long subjectTypeFk;
    private Long subjectFk;
    private String username;
    private String passw;
    private long status;
    private Date validFrom;
    private Date validTo;
    private Long createdBy;
    private Date created;
    private String passwordNeverExpires;

    public long getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(long userAccount) {
        this.userAccount = userAccount;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getPasswordNeverExpires() {
        return passwordNeverExpires;
    }

    public void setPasswordNeverExpires(String passwordNeverExpires) {
        this.passwordNeverExpires = passwordNeverExpires;
    }
}
