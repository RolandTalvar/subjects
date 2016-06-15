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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rolandtalvar on 02/06/16.
 */
@Controller
@RequestMapping("/subject")
@SessionAttributes({"person", "address", "enterprise", "employee", "contactListWrapper", "subjectAttributeListWrapper", "customerAttributeListWrapper"})
public class EditSubjectController {

    private static final Logger logger = LoggerFactory.getLogger("subjectLogger");

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

    @Autowired
    ContactDAO contactDAO;

    @Autowired
    ContactTypeDAO contactTypeDAO;

    @Autowired
    SubjectAttributeTypeRepository subjectAttributeTypeRepository;

    @Autowired
    SubjectAttributeRepository subjectAttributeRepository;

    @Autowired
    ContactRepository contactRepository;


    @RequestMapping(value = "/editPerson", method = RequestMethod.GET, params = "id")
    public String getPersonEditForm(@RequestParam("id") long id, Model model) {

        logger.info("Getting edit person form");

        final long subjectType = 1L;
        final long addressType = 1L;

        Person person = personRepository.findByPerson(id);
        model.addAttribute(person);

        Address address = addressRepository.findBySubjectTypeFkAndSubjectFkAndAddressTypeFk(subjectType, id, addressType);
        address = getAddress(addressType, id, subjectType, address);
        model.addAttribute(address);

        Customer customer = customerRepository.findBySubjectFkAndSubjectTypeFk(id, subjectType);
        boolean isCustomer;

        if (customer != null) {
            isCustomer = true;
        } else {
            isCustomer = false;
        }

        model.addAttribute("isCustomer", isCustomer);

        List<Contact> contacts = contactDAO.findAll(subjectType, id);
        ContactListWrapper contactListWrapper = new ContactListWrapper();
        contactListWrapper.setContactList(contacts);
        model.addAttribute(contactListWrapper);

        List<ContactType> contactTypes = contactTypeDAO.findAll();
        ContactTypeListWrapper contactTypeListWrapper = new ContactTypeListWrapper();
        contactTypeListWrapper.setContactTypeList(contactTypes);
        model.addAttribute(contactTypeListWrapper);

//        Subject attributes

        List<SubjectAttributeType> subjectAttributeTypeList = subjectAttributeTypeRepository.findBySubjectTypeFk(subjectType);
        SubjectAttributeTypeListWrapper subjectAttributeTypeListWrapper = new SubjectAttributeTypeListWrapper();
        subjectAttributeTypeListWrapper.setSubjectAttributeTypeList(subjectAttributeTypeList);
        model.addAttribute(subjectAttributeTypeListWrapper);

        SubjectAttributeListWrapper subjectAttributeListWrapper = getSubjectAttributeListWrapper(id, subjectType, subjectAttributeTypeList);
        model.addAttribute("subjectAttributeListWrapper", subjectAttributeListWrapper);

        //        Customer attributes

        SubjectAttributeTypeListWrapper customerAttributeTypeListWrapper = new SubjectAttributeTypeListWrapper();
        SubjectAttributeListWrapper customerAttributeListWrapper = new SubjectAttributeListWrapper();

        if (isCustomer) {
            List<SubjectAttributeType> customerAttributeTypeList = subjectAttributeTypeRepository.findBySubjectTypeFk(4L);
            customerAttributeTypeListWrapper.setSubjectAttributeTypeList(customerAttributeTypeList);

            customerAttributeListWrapper = getCustomerAttributeListWrapper(customer, customerAttributeTypeList);
        }

        model.addAttribute("customerAttributeTypeListWrapper", customerAttributeTypeListWrapper);
        model.addAttribute("customerAttributeListWrapper", customerAttributeListWrapper);

        return "editPerson";

    }



    @RequestMapping(value = "/editPerson", method = RequestMethod.POST)
    public String postPersonEditForm(@ModelAttribute("person") Person person,
                                     @ModelAttribute("address") Address address,
                                     @ModelAttribute("contactListWrapper") ContactListWrapper contactListWrapper,
                                     @ModelAttribute("subjectAttributeListWrapper") SubjectAttributeListWrapper subjectAttributeListWrapper,
                                     @ModelAttribute("customerAttributeListWrapper") SubjectAttributeListWrapper customerAttributeListWrapper,
                                     @RequestParam(required = false, value = "isCustomer") boolean isCustomer) {

        logger.info("Posting edit person form");

        final long subjectType = 1L;

        person.setUpdated(new java.sql.Timestamp(new java.util.Date().getTime()));

        personRepository.save(person);

        addressRepository.save(address);

        Customer customer = customerRepository.findBySubjectFkAndSubjectTypeFk(person.getPerson(), subjectType);
        if (customer == null) {
            customer = new Customer();
        }

        if (isCustomer) {
            customer.setSubjectFk(person.getPerson());
            customer.setSubjectTypeFk(subjectType);
            customerRepository.save(customer);
        }

        if (contactListWrapper != null && contactListWrapper.getContactList() != null) {
            for (Contact contact: contactListWrapper.getContactList()) {
                contactRepository.save(contact);
//                contactDAO.insertOrUpdate(contact);
            }
        }


        if (subjectAttributeListWrapper != null && subjectAttributeListWrapper.getSubjectAttributeList() != null) {
            for (SubjectAttribute subjectAttribute: subjectAttributeListWrapper.getSubjectAttributeList()) {
                subjectAttributeRepository.save(subjectAttribute);
            }
        }


        if (customerAttributeListWrapper != null && customerAttributeListWrapper.getCustomerAttributeList() != null) {
            for (SubjectAttribute subjectAttribute: customerAttributeListWrapper.getCustomerAttributeList()) {
                subjectAttributeRepository.save(subjectAttribute);
            }
        } else if (isCustomer) {

            List<SubjectAttributeType> subjectAttributeTypeList = subjectAttributeTypeRepository.findBySubjectTypeFk(4L);

            for (SubjectAttributeType type : subjectAttributeTypeList) {
                SubjectAttribute subjectAttribute = new SubjectAttribute();
                subjectAttribute.setSubjectFk(customer.getCustomer());
                subjectAttribute.setSubjectTypeFk(4L);
                subjectAttribute.setSubjectAttributeTypeFk(type.getSubjectAttributeType());
                subjectAttribute.setOrderby(type.getOrderby());
                subjectAttribute.setDataType(type.getDataType());
                subjectAttributeRepository.save(subjectAttribute);
            }
        }


        return "redirect:/subject/editPerson?id=" + person.getPerson();


    }

    @RequestMapping(value = "/editEnterprise", method = RequestMethod.GET, params = "id")
    public String getEnterpriseEditForm(@RequestParam("id") long id, Model model) {

        logger.info("Getting edit enterprise form");

        final long subjectType = 2L;
        final long addressType = 3L;

        Enterprise enterprise = enterpriseRepository.findByEnterprise(id);
        model.addAttribute(enterprise);

        Address address = addressRepository.findBySubjectTypeFkAndSubjectFkAndAddressTypeFk(subjectType, id, addressType);
        address = getAddress(addressType, id, subjectType, address);
        model.addAttribute(address);

        Customer customer = customerRepository.findBySubjectFkAndSubjectTypeFk(id, subjectType);
        boolean isCustomer;

        if (customer != null) {
            isCustomer = true;
        } else {
            isCustomer = false;
        }

        model.addAttribute("isCustomer", isCustomer);

        List<Contact> contacts = contactDAO.findAll(subjectType, id);
        ContactListWrapper contactListWrapper = new ContactListWrapper();
        contactListWrapper.setContactList(contacts);
        model.addAttribute(contactListWrapper);

        List<ContactType> contactTypes = contactTypeDAO.findAll();
        ContactTypeListWrapper contactTypeListWrapper = new ContactTypeListWrapper();
        contactTypeListWrapper.setContactTypeList(contactTypes);
        model.addAttribute(contactTypeListWrapper);

//        Subject attributes

        List<SubjectAttributeType> subjectAttributeTypeList = subjectAttributeTypeRepository.findBySubjectTypeFk(subjectType);
        SubjectAttributeTypeListWrapper subjectAttributeTypeListWrapper = new SubjectAttributeTypeListWrapper();
        subjectAttributeTypeListWrapper.setSubjectAttributeTypeList(subjectAttributeTypeList);
        model.addAttribute(subjectAttributeTypeListWrapper);

        SubjectAttributeListWrapper subjectAttributeListWrapper = getSubjectAttributeListWrapper(id, subjectType, subjectAttributeTypeList);
        model.addAttribute("subjectAttributeListWrapper", subjectAttributeListWrapper);



//        Customer attributes

        SubjectAttributeTypeListWrapper customerAttributeTypeListWrapper = new SubjectAttributeTypeListWrapper();
        SubjectAttributeListWrapper customerAttributeListWrapper = new SubjectAttributeListWrapper();

        if (isCustomer) {
            List<SubjectAttributeType> customerAttributeTypeList = subjectAttributeTypeRepository.findBySubjectTypeFk(4L);
            customerAttributeTypeListWrapper.setSubjectAttributeTypeList(customerAttributeTypeList);

            customerAttributeListWrapper = getCustomerAttributeListWrapper(customer, customerAttributeTypeList);
        }

        model.addAttribute("customerAttributeTypeListWrapper", customerAttributeTypeListWrapper);
        model.addAttribute("customerAttributeListWrapper", customerAttributeListWrapper);

        return "editEnterprise";


    }



    @RequestMapping(value = "/editEnterprise", method = RequestMethod.POST)
    public String postEnterpriseEditForm(@ModelAttribute("enterprise") Enterprise enterprise,
                                         @ModelAttribute("address") Address address,
                                         @ModelAttribute("contactListWrapper") ContactListWrapper contactListWrapper,
                                         @ModelAttribute("subjectAttributeListWrapper") SubjectAttributeListWrapper subjectAttributeListWrapper,
                                         @ModelAttribute("customerAttributeListWrapper") SubjectAttributeListWrapper customerAttributeListWrapper,
                                         @RequestParam(required = false, value = "isCustomer") boolean isCustomer) {

        logger.info("Posting edit enterprise form");

        final long subjectType = 2L;

        enterprise.setUpdated(new java.sql.Timestamp(new java.util.Date().getTime()));

        enterpriseRepository.save(enterprise);

        addressRepository.save(address);

        Customer customer = customerRepository.findBySubjectFkAndSubjectTypeFk(enterprise.getEnterprise(), subjectType);
        if (customer == null) {
            customer = new Customer();
        }

        if (isCustomer) {
            customer.setSubjectFk(enterprise.getEnterprise());
            customer.setSubjectTypeFk(subjectType);
            customerRepository.save(customer);
        }

        if (contactListWrapper != null && contactListWrapper.getContactList() != null) {
            for (Contact contact: contactListWrapper.getContactList()) {
                contactRepository.save(contact);
//                contactDAO.insertOrUpdate(contact);
            }
        }

        if (subjectAttributeListWrapper != null && subjectAttributeListWrapper.getSubjectAttributeList() != null) {
            for (SubjectAttribute subjectAttribute: subjectAttributeListWrapper.getSubjectAttributeList()) {
                subjectAttributeRepository.save(subjectAttribute);
            }
        }

        if (customerAttributeListWrapper != null && customerAttributeListWrapper.getCustomerAttributeList() != null) {
            for (SubjectAttribute subjectAttribute: customerAttributeListWrapper.getCustomerAttributeList()) {
                subjectAttributeRepository.save(subjectAttribute);
            }
        } else if (isCustomer) {

            List<SubjectAttributeType> subjectAttributeTypeList = subjectAttributeTypeRepository.findBySubjectTypeFk(4L);
            List<SubjectAttribute> customerAttributeList = new ArrayList<>();


            for (SubjectAttributeType type : subjectAttributeTypeList) {
                SubjectAttribute subjectAttribute = new SubjectAttribute();
                subjectAttribute.setSubjectTypeFk(4L);
                subjectAttribute.setSubjectFk(customer.getCustomer());
                subjectAttribute.setSubjectAttributeTypeFk(type.getSubjectAttributeType());
                subjectAttribute.setOrderby(type.getOrderby());
                subjectAttribute.setDataType(type.getDataType());
                subjectAttributeRepository.save(subjectAttribute);
                customerAttributeList.add(subjectAttribute);
            }
//            customerAttributeListWrapper.setCustomerAttributeList(customerAttributeList);
        }


        return "redirect:/subject/editEnterprise?id=" + enterprise.getEnterprise();


    }

    @RequestMapping(value = "/editEmployee", method = RequestMethod.GET, params = "id")
    public String getEmployeeEditForm(@RequestParam("id") long id, Model model) {

        logger.info("Getting edit employee form");

        final long subjectType = 3L;
        final long addressType = 1L;

        Employee employee = employeeRepository.findByEmployee(id);
        model.addAttribute(employee);

        Person person = personRepository.findByPerson(employee.getPersonFk());
        model.addAttribute(person);

        Address address = addressRepository.findBySubjectTypeFkAndSubjectFkAndAddressTypeFk(1L, person.getPerson(), addressType);
        address = getAddress(addressType, person.getPerson(), 1L, address);
        model.addAttribute(address);

        List<Enterprise> enterprises = enterpriseRepository.findAll();
        model.addAttribute("enterprises", enterprises);

        List<StructUnit> structUnits = structUnitRepository.findByEnterpriseFk(employee.getEnterpriseFk());
        model.addAttribute("structUnits", structUnits);

        List<Contact> contacts = contactDAO.findAll(1L, employee.getPersonFk());
        ContactListWrapper contactListWrapper = new ContactListWrapper();
        contactListWrapper.setContactList(contacts);
        model.addAttribute(contactListWrapper);

        List<ContactType> contactTypes = contactTypeDAO.findAll();
        ContactTypeListWrapper contactTypeListWrapper = new ContactTypeListWrapper();
        contactTypeListWrapper.setContactTypeList(contactTypes);
        model.addAttribute(contactTypeListWrapper);

        List<SubjectAttributeType> subjectAttributeTypeList = subjectAttributeTypeRepository.findBySubjectTypeFk(subjectType);
        SubjectAttributeTypeListWrapper subjectAttributeTypeListWrapper = new SubjectAttributeTypeListWrapper();
        subjectAttributeTypeListWrapper.setSubjectAttributeTypeList(subjectAttributeTypeList);
        model.addAttribute(subjectAttributeTypeListWrapper);

        SubjectAttributeListWrapper subjectAttributeListWrapper = getSubjectAttributeListWrapper(id, subjectType, subjectAttributeTypeList);
        model.addAttribute(subjectAttributeListWrapper);


        return "editEmployee";


    }



    @RequestMapping(value = "/editEmployee", method = RequestMethod.POST)
    public String postEmployeeEditForm(@ModelAttribute("person") Person person,
                                       @ModelAttribute("employee") Employee employee,
                                       @ModelAttribute("address") Address address,
                                       @ModelAttribute("contactListWrapper") ContactListWrapper contactListWrapper,
                                       @ModelAttribute("subjectAttributeListWrapper") SubjectAttributeListWrapper subjectAttributeListWrapper,
                                       @RequestParam(required = false, value = "selectedEnterprise") Long selectedEnterprise,
                                       @RequestParam(required = false, value = "selectedStructUnit") Long selectedStructUnit) {

        logger.info("Posting edit employee form");

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

        for (Contact contact: contactListWrapper.getContactList()) {
            contactRepository.save(contact);
//            contactDAO.insertOrUpdate(contact);
        }

        for (SubjectAttribute subjectAttribute: subjectAttributeListWrapper.getSubjectAttributeList()) {
            subjectAttributeRepository.save(subjectAttribute);
        }

        return "redirect:/subject/editEmployee?id=" + employee.getEmployee();

    }

    private Address getAddress(long addressType, long id, long subjectType, Address address) {
        if (address == null) {
            address = new Address();
            address.setAddressTypeFk(addressType);
            address.setSubjectFk(id);
            address.setSubjectTypeFk(subjectType);
        }
        return address;
    }

    private SubjectAttributeListWrapper getSubjectAttributeListWrapper(@RequestParam("id") long id, long subjectType, List<SubjectAttributeType> subjectAttributeTypeList) {
        SubjectAttributeListWrapper subjectAttributeListWrapper = new SubjectAttributeListWrapper();
        subjectAttributeListWrapper.setSubjectAttributeList(new ArrayList<>());

        for (SubjectAttributeType subjectAttributeType: subjectAttributeTypeList) {

            if (("Y").equalsIgnoreCase(subjectAttributeType.getMultipleAttributes())) {
                List<SubjectAttribute> subjectAttributeList = subjectAttributeRepository.findBySubjectFkAndSubjectTypeFkAndSubjectAttributeTypeFk(id, subjectType, subjectAttributeType.getSubjectAttributeType());

                if (subjectAttributeList.size() == 0) {
                    SubjectAttribute subjectAttribute = new SubjectAttribute();
                    subjectAttribute.setSubjectTypeFk(subjectType);
                    subjectAttribute.setSubjectFk(id);
                    subjectAttribute.setSubjectAttributeTypeFk(subjectAttributeType.getSubjectAttributeType());
                    subjectAttribute.setDataType(subjectAttributeType.getDataType());
                    subjectAttributeListWrapper.getSubjectAttributeList().add(subjectAttribute);

                } else {
                    for (SubjectAttribute subjectAttribute : subjectAttributeList) {
                        subjectAttributeListWrapper.getSubjectAttributeList().add(subjectAttribute);
                    }
                }

            } else {

                SubjectAttribute subjectAttribute = subjectAttributeRepository.findBySubjectTypeFkAndSubjectFkAndSubjectAttributeTypeFk(subjectType, id, subjectAttributeType.getSubjectAttributeType());

                if (subjectAttribute == null) {
                    subjectAttribute = new SubjectAttribute();
                    subjectAttribute.setSubjectTypeFk(subjectType);
                    subjectAttribute.setSubjectFk(id);
                    subjectAttribute.setSubjectAttributeTypeFk(subjectAttributeType.getSubjectAttributeType());
                    subjectAttribute.setDataType(subjectAttributeType.getDataType());
                }

                subjectAttributeListWrapper.getSubjectAttributeList().add(subjectAttribute);
            }

        }
        return subjectAttributeListWrapper;
    }

    private SubjectAttributeListWrapper getCustomerAttributeListWrapper(Customer customer, List<SubjectAttributeType> customerAttributeTypeList) {
        SubjectAttributeListWrapper customerAttributeListWrapper = new SubjectAttributeListWrapper();
        customerAttributeListWrapper.setCustomerAttributeList(new ArrayList<>());

        for (SubjectAttributeType subjectAttributeType: customerAttributeTypeList) {

            if (("Y").equalsIgnoreCase(subjectAttributeType.getMultipleAttributes())) {
                List<SubjectAttribute> subjectAttributeList = subjectAttributeRepository.findBySubjectFkAndSubjectTypeFkAndSubjectAttributeTypeFk(customer.getCustomer(), 4L, subjectAttributeType.getSubjectAttributeType());

                if (subjectAttributeList.size() == 0) {
                    SubjectAttribute subjectAttribute = new SubjectAttribute();
                    subjectAttribute.setSubjectTypeFk(4L);
                    subjectAttribute.setSubjectFk(customer.getCustomer());
                    subjectAttribute.setSubjectAttributeTypeFk(subjectAttributeType.getSubjectAttributeType());
                    subjectAttribute.setDataType(subjectAttributeType.getDataType());
                    customerAttributeListWrapper.getCustomerAttributeList().add(subjectAttribute);

                } else {
                    for (SubjectAttribute subjectAttribute : subjectAttributeList) {
                        customerAttributeListWrapper.getCustomerAttributeList().add(subjectAttribute);
                    }
                }

            } else {

                SubjectAttribute subjectAttribute = subjectAttributeRepository.findBySubjectTypeFkAndSubjectFkAndSubjectAttributeTypeFk(4L, customer.getCustomer(), subjectAttributeType.getSubjectAttributeType());

                if (subjectAttribute == null) {
                    subjectAttribute = new SubjectAttribute();
                    subjectAttribute.setSubjectTypeFk(4L);
                    subjectAttribute.setSubjectFk(customer.getCustomer());
                    subjectAttribute.setSubjectAttributeTypeFk(subjectAttributeType.getSubjectAttributeType());
                    subjectAttribute.setDataType(subjectAttributeType.getDataType());
                }

                customerAttributeListWrapper.getCustomerAttributeList().add(subjectAttribute);
            }

        }
        return customerAttributeListWrapper;
    }

}
