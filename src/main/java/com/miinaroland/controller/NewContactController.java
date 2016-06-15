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
public class NewContactController {

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


    @RequestMapping(value = "/addNewPersonContact", method = RequestMethod.GET, params = "id")
    public String getAddNewPersonContact(@RequestParam("id") long id, Model model) {

        logger.info("Getting new person contact form");

        Contact contact = new Contact();
        model.addAttribute(contact);

        Person person = personRepository.findByPerson(id);
        model.addAttribute(person);

        List<ContactType> contactTypes = contactTypeDAO.findAll();
        ContactTypeListWrapper contactTypeListWrapper = new ContactTypeListWrapper();
        contactTypeListWrapper.setContactTypeList(contactTypes);
        model.addAttribute(contactTypeListWrapper);

        return "addNewPersonContact";
    }

    @RequestMapping(value = "/addNewPersonContact", method = RequestMethod.POST)
    public String postAddNewPersonContact(@ModelAttribute("person") Person person,
                                          @ModelAttribute Contact contact) {

        logger.info("Posting new person contact form");


        List<Contact> contactList = contactDAO.findAllOfType(1L, person.getPerson(), contact.getContactTypeFk());

        Long previousOrderBy;
        if (contactList.size() == 0) {
            previousOrderBy = 0L;
        } else {
            previousOrderBy = contactList.get(contactList.size() - 1).getOrderby();
        }

        if (previousOrderBy == null) {
            previousOrderBy = 0L;
        }

        long lastId = contactDAO.getLastId();

        contact.setSubjectFk(person.getPerson());
        contact.setSubjectTypeFk(1L);
        contact.setOrderby(previousOrderBy + 1);
        contact.setContact(lastId + 1);
//        contactDAO.insertOrUpdate(contact);
        contactRepository.save(contact);

        return "redirect:/subject/editPerson?id=" + person.getPerson();
    }

    @RequestMapping(value = "/addNewEnterpriseContact", method = RequestMethod.GET, params = "id")
    public String getAddNewEnterpriseContact(@RequestParam("id") long id, Model model) {

        logger.info("Getting new enterprise contact form");

        Contact contact = new Contact();
        model.addAttribute(contact);

        Enterprise enterprise = enterpriseRepository.findByEnterprise(id);
        model.addAttribute(enterprise);

        List<ContactType> contactTypes = contactTypeDAO.findAll();
        ContactTypeListWrapper contactTypeListWrapper = new ContactTypeListWrapper();
        contactTypeListWrapper.setContactTypeList(contactTypes);
        model.addAttribute(contactTypeListWrapper);

        return "addNewEnterpriseContact";
    }

    @RequestMapping(value = "/addNewEnterpriseContact", method = RequestMethod.POST)
    public String postAddNewEnterpriseContact(@ModelAttribute("enterprise") Enterprise enterprise,
                                          @ModelAttribute Contact contact) {

        logger.info("Posting new enterprise contact form");


        List<Contact> contactList = contactDAO.findAllOfType(2L, enterprise.getEnterprise(), contact.getContactTypeFk());

        Long previousOrderBy;
        if (contactList.size() == 0) {
            previousOrderBy = 0L;
        } else {
            previousOrderBy = contactList.get(contactList.size() - 1).getOrderby();
        }

        if (previousOrderBy == null) {
            previousOrderBy = 0L;
        }

        long lastId = contactDAO.getLastId();

        contact.setSubjectFk(enterprise.getEnterprise());
        contact.setSubjectTypeFk(2L);
        contact.setOrderby(previousOrderBy + 1);
        contact.setContact(lastId + 1);
//        contactDAO.insertOrUpdate(contact);
        contactRepository.save(contact);

        return "redirect:/subject/editEnterprise?id=" + enterprise.getEnterprise();
    }

    @RequestMapping(value = "/addNewEmployeeContact", method = RequestMethod.GET, params = "id")
    public String getAddNewEmployeeContact(@RequestParam("id") long id, Model model) {

        logger.info("Getting new employee contact form");

        Contact contact = new Contact();
        model.addAttribute(contact);

        Employee employee = employeeRepository.findByEmployee(id);

        Person person = personRepository.findByPerson(employee.getPersonFk());
        model.addAttribute(person);

        List<ContactType> contactTypes = contactTypeDAO.findAll();
        ContactTypeListWrapper contactTypeListWrapper = new ContactTypeListWrapper();
        contactTypeListWrapper.setContactTypeList(contactTypes);
        model.addAttribute(contactTypeListWrapper);

        return "addNewEmployeeContact";
    }

    @RequestMapping(value = "/addNewEmployeeContact", method = RequestMethod.POST)
    public String postAddNewEmployeeContact(@ModelAttribute("person") Person person,
                                            @ModelAttribute("employee") Employee employee,
                                          @ModelAttribute Contact contact) {

        logger.info("Posting new employee contact form");


        List<Contact> contactList = contactDAO.findAllOfType(1L, employee.getPersonFk(), contact.getContactTypeFk());

        Long previousOrderBy;
        if (contactList.size() == 0) {
            previousOrderBy = 0L;
        } else {
            previousOrderBy = contactList.get(contactList.size() - 1).getOrderby();
        }

        if (previousOrderBy == null) {
            previousOrderBy = 0L;
        }

        long lastId = contactDAO.getLastId();

        contact.setSubjectFk(employee.getPersonFk());
        contact.setSubjectTypeFk(1L);
        contact.setOrderby(previousOrderBy + 1);
        contact.setContact(lastId + 1);
//        contactDAO.insertOrUpdate(contact);
        contactRepository.save(contact);

        return "redirect:/subject/editEmployee?id=" + employee.getEmployee();
    }
}
