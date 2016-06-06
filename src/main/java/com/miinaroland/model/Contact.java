package com.miinaroland.model;

import javax.persistence.*;

/**
 * Created by rolandtalvar on 02/06/16.
 */
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_id")
    @SequenceGenerator(name="contact_id", sequenceName="contact_id", allocationSize=1)
    private long contact;
    private Long subjectTypeFk;
    private Long subjectFk;
    private Long contactTypeFk;
    private String valueText;
    private Long orderby;
    private String note;

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
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

    public Long getContactTypeFk() {
        return contactTypeFk;
    }

    public void setContactTypeFk(Long contactTypeFk) {
        this.contactTypeFk = contactTypeFk;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public Long getOrderby() {
        return orderby;
    }

    public void setOrderby(Long orderby) {
        this.orderby = orderby;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
