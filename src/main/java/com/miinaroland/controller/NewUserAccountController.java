package com.miinaroland.controller;

import com.miinaroland.model.*;
import com.miinaroland.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miinaroland on 02/06/16.
 */
@Controller
@RequestMapping("/subject")
@SessionAttributes({ "employee", "userAccount"})
public class NewUserAccountController {

    private static final Logger logger = LoggerFactory.getLogger("subjectLogger");

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    PersonRepository personRepository;


    @RequestMapping(value = "/addNewUserAccount", method = RequestMethod.GET)
    public String getAddNewUserAccount(Model model) {

        logger.info("Getting new User Account form");

        UserAccount userAccount = new UserAccount();
        model.addAttribute(userAccount);

        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeForm> employeeFormList = new ArrayList<>();
        for (Employee employee: employeeList) {
            Person person = personRepository.findByPerson(employee.getPersonFk());
            EmployeeForm employeeForm = new EmployeeForm();
            employeeForm.setEmployee(employee.getEmployee());
            employeeForm.setFirstName(person.getFirstName());
            employeeForm.setLastName(person.getLastName());

            employeeFormList.add(employeeForm);
        }

        EmployeeListWrapper employeeListWrapper = new EmployeeListWrapper();
        employeeListWrapper.setEmployeeList(employeeFormList);
        model.addAttribute(employeeListWrapper);

        return "addNewUserAccount";
    }

    @RequestMapping(value = "/addNewUserAccount", method = RequestMethod.POST)
    public String postAddNewUserAccount(@ModelAttribute("userAccount") UserAccount userAccount) {

        logger.info("Posting new user account form");

        userAccount.setSubjectTypeFk(3L);
        userAccount.setStatus(1L);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(userAccount.getPassw());
        userAccount.setPassw(hashedPassword);

        userAccountRepository.save(userAccount);

        return "redirect:/subject/addNewUserAccount";
    }
}
