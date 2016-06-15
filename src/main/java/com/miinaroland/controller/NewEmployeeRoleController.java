package com.miinaroland.controller;

import com.miinaroland.dao.ContactDAO;
import com.miinaroland.dao.ContactTypeDAO;
import com.miinaroland.model.*;
import com.miinaroland.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@SessionAttributes({"person", "address", "enterprise", "employee", "contactListWrapper"})
public class NewEmployeeRoleController {

    private static final Logger logger = LoggerFactory.getLogger("subjectLogger");

    @Autowired
    SubjectTypeRepository subjectTypeRepository;

    @Autowired
    ContactRepository contactRepository;

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

    @Autowired
    ContactDAO contactDAO;

    @Autowired
    ContactTypeDAO contactTypeDAO;

    @Autowired
    EmployeeRoleRepository employeeRoleRepository;

    @Autowired
    EmployeeRoleTypeRepository employeeRoleTypeRepository;


    @RequestMapping(value = "/addNewEmployeeRole", method = RequestMethod.GET, params = "id")
    public String getAddNewEmployeeRole(@RequestParam("id") long id, Model model) {

        logger.info("Getting new employee role form");

        EmployeeRole employeeRole = new EmployeeRole();
        model.addAttribute(employeeRole);

        Employee employee = employeeRepository.findByEmployee(id);

        Person person = personRepository.findByPerson(employee.getPersonFk());
        model.addAttribute(person);

        List<EmployeeRoleType> employeeRoleTypes = employeeRoleTypeRepository.findAll();
        EmployeeRoleTypeListWrapper employeeRoleTypeListWrapper = new EmployeeRoleTypeListWrapper();
        employeeRoleTypeListWrapper.setEmployeeRoleTypeList(employeeRoleTypes);
        model.addAttribute(employeeRoleTypeListWrapper);

        return "addNewEmployeeRole";
    }

    @RequestMapping(value = "/addNewEmployeeRole", method = RequestMethod.POST)
    public String postAddNewEmployeeRole(@ModelAttribute("person") Person person,
                                            @ModelAttribute("employee") Employee employee,
                                          @ModelAttribute EmployeeRole employeeRole) {

        logger.info("Posting new employee role form");

        employeeRole.setEmployeeFk(employee.getEmployee());
        employeeRole.setActive("Y");
        employeeRoleRepository.save(employeeRole);

        return "redirect:/subject/editEmployee?id=" + employee.getEmployee();
    }
}
