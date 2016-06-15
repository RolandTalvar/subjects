package com.miinaroland.controller;

import com.miinaroland.dao.ContactDAO;
import com.miinaroland.model.*;
import com.miinaroland.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by rolandtalvar on 02/06/16.
 */
@Controller
@RequestMapping("/subject")
public class AddSubjectController {

    @Autowired
    SubjectTypeRepository subjectTypeRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    EnterpriseRepository enterpriseRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    StructUnitRepository structUnitRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    SubjectAttributeTypeRepository subjectAttributeTypeRepository;

    @Autowired
    ContactDAO contactDAO;

    @Autowired
    SubjectAttributeRepository subjectAttributeRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getSubjectAddForm(@ModelAttribute SubjectType subjectType, Model model) {
        List<SubjectType> subjectTypes = subjectTypeRepository.findAll();
        model.addAttribute("subjectTypes", subjectTypes);
        return "subject";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postSubjectAddForm(@ModelAttribute SubjectType subjectType) {

        switch ((int) subjectType.getSubjectType()) {
            case 1:
                return "redirect:/subject/addPerson";
            case 2:
                return "redirect:/subject/addEnterprise";
            case 3:
                return "redirect:/subject/addEmployee";
            default:
                return "subject";
        }

    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.GET)
    public String getPersonAddForm(@ModelAttribute Person person, @ModelAttribute Address address) {

        return "addPerson";


    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String postPersonAddForm(@ModelAttribute Person person, @ModelAttribute Address address) {

        person.setCreated(new java.sql.Timestamp(new java.util.Date().getTime()));
        person.setUpdated(new java.sql.Timestamp(new java.util.Date().getTime()));

        personRepository.save(person);

        address.setSubjectFk(person.getPerson());
        address.setSubjectTypeFk(1L);
        address.setAddressTypeFk(1L);

        addressRepository.save(address);

        long lastId = contactDAO.getLastId();
        Contact contact = new Contact();
        contact.setContact(lastId + 1);
        contact.setSubjectFk(person.getPerson());
        contact.setSubjectTypeFk(1L);
        contact.setOrderby(1L);
        contact.setContactTypeFk(1L);
        contactRepository.save(contact);

        Contact contact2 = new Contact();
        contact2.setContact(lastId + 2);
        contact2.setSubjectFk(person.getPerson());
        contact2.setSubjectTypeFk(1L);
        contact2.setOrderby(1L);
        contact2.setContactTypeFk(2L);
        contactRepository.save(contact2);

        List<SubjectAttributeType> subjectAttributeTypeList = subjectAttributeTypeRepository.findBySubjectTypeFk(1L);

        for (SubjectAttributeType type : subjectAttributeTypeList) {
            SubjectAttribute subjectAttribute = new SubjectAttribute();
            subjectAttribute.setSubjectFk(person.getPerson());
            subjectAttribute.setSubjectTypeFk(1L);
            subjectAttribute.setSubjectAttributeTypeFk(type.getSubjectAttributeType());
            subjectAttribute.setOrderby(type.getOrderby());
            subjectAttribute.setDataType(type.getDataType());
            subjectAttributeRepository.save(subjectAttribute);
        }

        return "redirect:/subject/editPerson?id=" + person.getPerson();


    }

    @RequestMapping(value = "/addEnterprise", method = RequestMethod.GET)
    public String getEnterpriseAddForm(@ModelAttribute Enterprise enterprise, @ModelAttribute Address address) {

        return "addEnterprise";


    }

    @RequestMapping(value = "/addEnterprise", method = RequestMethod.POST)
    public String postEnterpriseAddForm(@ModelAttribute Enterprise enterprise, @ModelAttribute Address address) {

        enterprise.setCreated(new java.sql.Timestamp(new java.util.Date().getTime()));
        enterprise.setUpdated(new java.sql.Timestamp(new java.util.Date().getTime()));

        enterpriseRepository.save(enterprise);

        address.setSubjectFk(enterprise.getEnterprise());
        address.setSubjectTypeFk(2L);
        address.setAddressTypeFk(3L);

        addressRepository.save(address);

        long lastId = contactDAO.getLastId();
        Contact contact = new Contact();
        contact.setContact(lastId + 1);
        contact.setSubjectFk(enterprise.getEnterprise());
        contact.setSubjectTypeFk(2L);
        contact.setOrderby(1L);
        contact.setContactTypeFk(1L);
        contactRepository.save(contact);

        Contact contact2 = new Contact();
        contact2.setContact(lastId + 2);
        contact2.setSubjectFk(enterprise.getEnterprise());
        contact2.setSubjectTypeFk(2L);
        contact2.setOrderby(1L);
        contact2.setContactTypeFk(2L);
        contactRepository.save(contact2);

        List<SubjectAttributeType> subjectAttributeTypeList = subjectAttributeTypeRepository.findBySubjectTypeFk(2L);

        for (SubjectAttributeType type : subjectAttributeTypeList) {
            SubjectAttribute subjectAttribute = new SubjectAttribute();
            subjectAttribute.setSubjectFk(enterprise.getEnterprise());
            subjectAttribute.setSubjectTypeFk(2L);
            subjectAttribute.setSubjectAttributeTypeFk(type.getSubjectAttributeType());
            subjectAttribute.setOrderby(type.getOrderby());
            subjectAttribute.setDataType(type.getDataType());
            subjectAttributeRepository.save(subjectAttribute);
        }

        return "redirect:/subject/editEnterprise?id=" + enterprise.getEnterprise();


    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
    public String getEmployeeAddForm(@ModelAttribute Person person, @ModelAttribute Employee employee, @ModelAttribute Address address,
                                     @ModelAttribute Enterprise enterprise, @ModelAttribute StructUnit structUnit, Model model) {

        List<Enterprise> enterprises = enterpriseRepository.findAll();
        model.addAttribute("enterprises", enterprises);

        List<StructUnit> structUnits = structUnitRepository.findAll();
        model.addAttribute("structUnits", structUnits);

        return "addEmployee";


    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String postEmployeeAddForm(@ModelAttribute Person person, @ModelAttribute Employee employee, @ModelAttribute Address address,
                                      @ModelAttribute Enterprise enterprise, @ModelAttribute StructUnit structUnit) {

        person.setCreated(new java.sql.Timestamp(new java.util.Date().getTime()));
        person.setUpdated(new java.sql.Timestamp(new java.util.Date().getTime()));

        personRepository.save(person);

        employee.setStructUnitFk(structUnit.getStructUnit());
        employee.setEnterpriseFk(enterprise.getEnterprise());
        employee.setPersonFk(person.getPerson());
        employee.setActive("Y");

        employeeRepository.save(employee);

        address.setSubjectFk(person.getPerson());
        address.setSubjectTypeFk(1L);
        address.setAddressTypeFk(1L);

        addressRepository.save(address);

        long lastId = contactDAO.getLastId();
        Contact contact = new Contact();
        contact.setContact(lastId + 1);
        contact.setSubjectFk(employee.getPersonFk());
        contact.setSubjectTypeFk(1L);
        contact.setOrderby(1L);
        contact.setContactTypeFk(1L);
        contactRepository.save(contact);

        Contact contact2 = new Contact();
        contact2.setContact(lastId + 2);
        contact2.setSubjectFk(employee.getPersonFk());
        contact2.setSubjectTypeFk(1L);
        contact2.setOrderby(1L);
        contact2.setContactTypeFk(2L);
        contactRepository.save(contact2);

        List<SubjectAttributeType> subjectAttributeTypeList = subjectAttributeTypeRepository.findBySubjectTypeFk(3L);

        for (SubjectAttributeType type : subjectAttributeTypeList) {
            SubjectAttribute subjectAttribute = new SubjectAttribute();
            subjectAttribute.setSubjectFk(employee.getEmployee());
            subjectAttribute.setSubjectTypeFk(3L);
            subjectAttribute.setSubjectAttributeTypeFk(type.getSubjectAttributeType());
            subjectAttribute.setOrderby(type.getOrderby());
            subjectAttribute.setDataType(type.getDataType());
            subjectAttributeRepository.save(subjectAttribute);
        }

        return "redirect:/subject/editEmployee?id=" + employee.getEmployee();


    }
}
