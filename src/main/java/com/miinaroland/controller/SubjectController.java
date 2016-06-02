package com.miinaroland.controller;

import com.miinaroland.model.*;
import com.miinaroland.repository.SubjectTypeRepository;
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
public class SubjectController {

    @Autowired
    SubjectTypeRepository subjectTypeRepository;

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
            case 4:
                return "redirect:/subject/addCustomer";
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

        address.setSubjectFk(person.getPerson());
        address.setSubjectTypeFk(1L);
        address.setAddressTypeFk(1L);
        return "addPerson";


    }

    @RequestMapping(value = "/addEnterprise", method = RequestMethod.GET)
    public String getEnterpriseAddForm(@ModelAttribute Enterprise enterprise, @ModelAttribute Address address) {

        return "addEnterprise";


    }

    @RequestMapping(value = "/addEnterprise", method = RequestMethod.POST)
    public String postEnterpriseAddForm(@ModelAttribute Enterprise enterprise, @ModelAttribute Address address) {

        address.setSubjectFk(enterprise.getEnterprise());
        address.setSubjectTypeFk(2L);
        address.setAddressTypeFk(3L);
        return "addEnterprise";


    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
    public String getEmployeeAddForm(@ModelAttribute Employee employee) {

        return "addEmployee";


    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String postEmployeeAddForm(@ModelAttribute Employee employee) {

        return "addEmployee";


    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public String getCustomerAddForm(@ModelAttribute Customer customer) {

        return "addCustomer";


    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public String postCustomerAddForm(@ModelAttribute Customer customer) {

        return "addCustomer";


    }
}
