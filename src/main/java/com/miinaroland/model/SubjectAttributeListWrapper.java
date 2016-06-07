package com.miinaroland.model;

import java.util.List;

/**
 * Created by rolandtalvar on 06/06/16.
 */
public class SubjectAttributeListWrapper {

    private List<SubjectAttribute> subjectAttributeList;
    private List<SubjectAttribute> customerAttributeList;

    public List<SubjectAttribute> getSubjectAttributeList() {
        return subjectAttributeList;
    }

    public void setSubjectAttributeList(List<SubjectAttribute> subjectAttributeList) {
        this.subjectAttributeList = subjectAttributeList;
    }

    public List<SubjectAttribute> getCustomerAttributeList() {
        return customerAttributeList;
    }

    public void setCustomerAttributeList(List<SubjectAttribute> customerAttributeList) {
        this.customerAttributeList = customerAttributeList;
    }
}
