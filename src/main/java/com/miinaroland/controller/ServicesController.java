package com.miinaroland.controller;

import com.google.gson.Gson;
import com.miinaroland.dao.EnterpriseSearchDAO;
import com.miinaroland.model.Customer;
import com.miinaroland.model.Employee;
import com.miinaroland.model.Enterprise;
import com.miinaroland.model.Person;
import com.miinaroland.dao.PersonSearchDAO;
import com.miinaroland.repository.CustomerRepository;
import com.miinaroland.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/subject/search/")
public class ServicesController {

    @Autowired
    PersonSearchDAO personSearchDAO;

    @Autowired
    EnterpriseSearchDAO enterpriseSearchDAO;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    private static final Logger logger = LoggerFactory.getLogger("subjectLogger");

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String getPersonList(@RequestParam(value = "firstName", required = false) String firstName,
                                 @RequestParam(value = "lastName", required = false) String lastName,
                                 @RequestParam(value = "identityCode", required = false) String identityCode,
                                 @RequestParam(value = "birthDate", required = false) java.sql.Date birthDate) {

        logger.info("Doing person search");

        List<Person> personList = personSearchDAO.search(firstName, lastName, identityCode, birthDate);

        Gson gson = new Gson();
        return gson.toJson(personList);
    }

    @RequestMapping(value = "/enterprise", method = RequestMethod.GET)
    public String getEnterpriseList(@RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "fullName", required = false) String fullName) {

        logger.info("Doing enterprise search");

        List<Enterprise> enterpriseList = enterpriseSearchDAO.search(name, fullName);

        Gson gson = new Gson();
        return gson.toJson(enterpriseList);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getEmployeeList(@RequestParam(value = "firstName", required = false) String firstName,
                                 @RequestParam(value = "lastName", required = false) String lastName,
                                 @RequestParam(value = "identityCode", required = false) String identityCode,
                                 @RequestParam(value = "birthDate", required = false) java.sql.Date birthDate) {

        logger.info("Doing employee search");

         List<Person> personList = personSearchDAO.search(firstName, lastName, identityCode, birthDate);
        List<Employee> employeeList = new ArrayList<>();

        for (Person person : personList) {
            long personFk = person.getPerson();
            Employee employee = employeeRepository.findByPersonFk(personFk);
            if (employee != null) {
                employeeList.add(employee);
            }

        }

        Gson gson = new Gson();
        return gson.toJson(employeeList);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String getCustomerList(@RequestParam(value = "firstName", required = false) String firstName,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "identityCode", required = false) String identityCode,
                                  @RequestParam(value = "birthDate", required = false) java.sql.Date birthDate) {

        logger.info("Doing employee search");

        List<Person> personList = personSearchDAO.search(firstName, name, identityCode, birthDate);
        List<Enterprise> enterpriseList = enterpriseSearchDAO.search(name, null);

        List<Customer> customerList = new ArrayList<>();

        for (Person person : personList) {
            long personFk = person.getPerson();
            Customer customer = customerRepository.findBySubjectFkAndSubjectTypeFk(personFk, 1L);
            if (customer != null) {
                customerList.add(customer);
            }
        }

        for (Enterprise enterprise : enterpriseList) {
            long enterpriseFk = enterprise.getEnterprise();
            Customer customer = customerRepository.findBySubjectFkAndSubjectTypeFk(enterpriseFk, 2L);
            if (customer != null) {
                customerList.add(customer);
            }

        }

        Gson gson = new Gson();
        return gson.toJson(customerList);
    }

}
