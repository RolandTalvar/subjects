package com.miinaroland.controller;

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

        return "addPerson";


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

        return "addEnterprise";


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
        address.setSubjectTypeFk(3L);
        address.setAddressTypeFk(1L);

        addressRepository.save(address);

        return "addEmployee";


    }
}
