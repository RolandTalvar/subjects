package com.miinaroland.controller;

import com.miinaroland.model.*;
import com.miinaroland.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by rolandtalvar on 02/06/16.
 */
@Controller
@RequestMapping("/subject")
@SessionAttributes({"person", "address", "enterprise", "employee"})
public class EditSubjectController {

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
    CustomerRepository customerRepository;

    @RequestMapping(value = "/editPerson", method = RequestMethod.GET, params = "id")
    public String getPersonEditForm(@RequestParam("id") long id, Model model) {

        Person person = personRepository.findByPerson(id);
        model.addAttribute(person);

        Address address = addressRepository.findBySubjectTypeFkAndSubjectFkAndAddressTypeFk(1L, id, 1L);
        model.addAttribute(address);

        Customer customer = customerRepository.findBySubjectFkAndSubjectTypeFk(id, 1L);
        boolean isCustomer;

        if (customer != null) {
            isCustomer = true;
        } else {
            isCustomer = false;
        }

        model.addAttribute("isCustomer", isCustomer);

        return "editPerson";

    }

    @RequestMapping(value = "/editPerson", method = RequestMethod.POST)
    public String postPersonEditForm(@ModelAttribute("person") Person person, @ModelAttribute("address") Address address, @RequestParam(required = false, value = "isCustomer") boolean isCustomer) {

        person.setUpdated(new java.sql.Timestamp(new java.util.Date().getTime()));

        personRepository.save(person);

        addressRepository.save(address);

        if (isCustomer) {
            Customer customer = new Customer();
            customer.setSubjectFk(person.getPerson());
            customer.setSubjectTypeFk(1L);
            customerRepository.save(customer);
        }

        return "redirect:/subject/editPerson?id=" + person.getPerson();


    }

    @RequestMapping(value = "/editEnterprise", method = RequestMethod.GET, params = "id")
    public String getEnterpriseEditForm(@RequestParam("id") long id, Model model) {

        Enterprise enterprise = enterpriseRepository.findByEnterprise(id);
        model.addAttribute(enterprise);

        Address address = addressRepository.findBySubjectTypeFkAndSubjectFkAndAddressTypeFk(2L, id, 3L);
        model.addAttribute(address);

        Customer customer = customerRepository.findBySubjectFkAndSubjectTypeFk(id, 2L);
        boolean isCustomer;

        if (customer != null) {
            isCustomer = true;
        } else {
            isCustomer = false;
        }

        model.addAttribute("isCustomer", isCustomer);

        return "editEnterprise";


    }

    @RequestMapping(value = "/editEnterprise", method = RequestMethod.POST)
    public String postEnterpriseEditForm(@ModelAttribute("enterprise") Enterprise enterprise, @ModelAttribute("address") Address address, @RequestParam(required = false, value = "isCustomer") boolean isCustomer) {

        enterprise.setUpdated(new java.sql.Timestamp(new java.util.Date().getTime()));

        enterpriseRepository.save(enterprise);

        addressRepository.save(address);

        if (isCustomer) {
            Customer customer = new Customer();
            customer.setSubjectFk(enterprise.getEnterprise());
            customer.setSubjectTypeFk(2L);
            customerRepository.save(customer);
        }

        return "redirect:/subject/editEnterprise?id=" + enterprise.getEnterprise();


    }

    @RequestMapping(value = "/editEmployee", method = RequestMethod.GET, params = "id")
    public String getEmployeeEditForm(@RequestParam("id") long id, Model model) {

        Employee employee = employeeRepository.findByEmployee(id);
        model.addAttribute(employee);

        Person person = personRepository.findByPerson(employee.getPersonFk());
        model.addAttribute(person);

        Address address = addressRepository.findBySubjectTypeFkAndSubjectFkAndAddressTypeFk(3L, id, 1L);
        model.addAttribute(address);

        List<Enterprise> enterprises = enterpriseRepository.findAll();
        model.addAttribute("enterprises", enterprises);

        List<StructUnit> structUnits = structUnitRepository.findByEnterpriseFk(employee.getEnterpriseFk());
        model.addAttribute("structUnits", structUnits);

        return "editEmployee";


    }

    @RequestMapping(value = "/editEmployee", method = RequestMethod.POST)
    public String postEmployeeEditForm(@ModelAttribute("person") Person person,
                                       @ModelAttribute("employee") Employee employee,
                                       @ModelAttribute("address") Address address,
                                       @RequestParam(required = false, value = "selectedEnterprise") Long selectedEnterprise,
                                       @RequestParam(required = false, value = "selectedStructUnit") Long selectedStructUnit) {

        person.setUpdated(new java.sql.Timestamp(new java.util.Date().getTime()));

        personRepository.save(person);

        if (selectedEnterprise != null) {
            employee.setEnterpriseFk(selectedEnterprise);
        }
        if (selectedStructUnit != null) {
            employee.setStructUnitFk(selectedStructUnit);
        }

        employeeRepository.save(employee);

        addressRepository.save(address);

        return "redirect:/subject/editEmployee?id=" + employee.getEmployee();


    }
}
