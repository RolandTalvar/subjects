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
public class AdditionalAddressesController {

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


    @RequestMapping(value = "/additionalPersonAddresses", method = RequestMethod.GET, params = "id")
    public String getAdditionalPersonAddresses(@RequestParam("id") long id, Model model) {

        Person person = personRepository.findByPerson(id);
        model.addAttribute(person);

        List<Address> addressList = addressRepository.findBySubjectTypeFkAndAddressTypeFkAndSubjectFk(1L, 2L, id);
        model.addAttribute("addressList", addressList);

        return "additionalPersonAddresses";

    }

    @RequestMapping(value = "/additionalEnterpriseAddresses", method = RequestMethod.GET, params = "id")
    public String getAdditionalEnterpriseAddresses(@RequestParam("id") long id, Model model) {

        Enterprise enterprise = enterpriseRepository.findByEnterprise(id);
        model.addAttribute(enterprise);

        List<Address> addressList = addressRepository.findBySubjectTypeFkAndAddressTypeFkAndSubjectFk(2L, 2L, id);
        model.addAttribute("addressList", addressList);

        return "additionalEnterpriseAddresses";

    }

    @RequestMapping(value = "/additionalEmployeeAddresses", method = RequestMethod.GET, params = "id")
    public String getAdditionalEmployeeAddresses(@RequestParam("id") long id, Model model) {

        Employee employee = employeeRepository.findByEmployee(id);
        model.addAttribute(employee);

        Person person = personRepository.findByPerson(employee.getPersonFk());
        model.addAttribute(person);

        List<Address> addressList = addressRepository.findBySubjectTypeFkAndAddressTypeFkAndSubjectFk(1L, 2L, person.getPerson());
        model.addAttribute("addressList", addressList);

        return "additionalEmployeeAddresses";

    }

    @RequestMapping(value = "/editAdditionalPersonAddress", method = RequestMethod.GET, params = "id")
    public String getAdditionalPersonAddressEditForm(@RequestParam("id") long id, Model model, @ModelAttribute("person") Person person) {

        Address address = addressRepository.findByAddress(id);
        model.addAttribute(address);

        return "editAdditionalPersonAddress";

    }

    @RequestMapping(value = "/editAdditionalPersonAddress", method = RequestMethod.POST)
    public String postAdditionalPersonAddressEditForm(@ModelAttribute("address") Address address, @ModelAttribute("person") Person person) {

        addressRepository.save(address);

        return "redirect:/subject/additionalPersonAddresses?id=" + person.getPerson();

    }

    @RequestMapping(value = "/addAdditionalPersonAddress", method = RequestMethod.GET)
    public String getAdditionalPersonAddressAddForm(@ModelAttribute("person") Person person, Model model) {

        Address address = new Address();
        model.addAttribute(address);

        return "addAdditionalPersonAddress";

    }

    @RequestMapping(value = "/addAdditionalPersonAddress", method = RequestMethod.POST)
    public String postAdditionalPersonAddressAddForm(@ModelAttribute("address") Address address, @ModelAttribute("person") Person person) {

        address.setSubjectFk(person.getPerson());
        address.setSubjectTypeFk(1L);
        address.setAddressTypeFk(2L);

        addressRepository.save(address);

        return "redirect:/subject/additionalPersonAddresses?id=" + person.getPerson();

    }



    @RequestMapping(value = "/editAdditionalEnterpriseAddress", method = RequestMethod.GET, params = "id")
    public String getAdditionalEnterpriseAddressEditForm(@RequestParam("id") long id, Model model, @ModelAttribute("enterprise") Enterprise enterprise) {

        Address address = addressRepository.findByAddress(id);
        model.addAttribute(address);

        return "editAdditionalEnterpriseAddress";

    }

    @RequestMapping(value = "/editAdditionalEnterpriseAddress", method = RequestMethod.POST)
    public String postAdditionalEnterpriseAddressEditForm(@ModelAttribute("address") Address address, @ModelAttribute("enterprise") Enterprise enterprise) {

        addressRepository.save(address);

        return "redirect:/subject/additionalEnterpriseAddresses?id=" + enterprise.getEnterprise();

    }

    @RequestMapping(value = "/addAdditionalEnterpriseAddress", method = RequestMethod.GET)
    public String getAdditionalEnterpriseAddressAddForm(@ModelAttribute("enterprise") Enterprise enterprise, Model model) {

        Address address = new Address();
        model.addAttribute(address);

        return "addAdditionalEnterpriseAddress";

    }

    @RequestMapping(value = "/addAdditionalEnterpriseAddress", method = RequestMethod.POST)
    public String postAdditionalEnterpriseAddressAddForm(@ModelAttribute("address") Address address, @ModelAttribute("enterprise") Enterprise enterprise) {

        address.setSubjectFk(enterprise.getEnterprise());
        address.setSubjectTypeFk(2L);
        address.setAddressTypeFk(2L);

        addressRepository.save(address);

        return "redirect:/subject/additionalEnterpriseAddresses?id=" + enterprise.getEnterprise();

    }



    @RequestMapping(value = "/editAdditionalEmployeeAddress", method = RequestMethod.GET, params = "id")
    public String getAdditionalEmployeeAddressEditForm(@RequestParam("id") long id, Model model,
                                                       @ModelAttribute("person") Person person,
                                                       @ModelAttribute("employee") Employee employee) {

        Address address = addressRepository.findByAddress(id);
        model.addAttribute(address);

        return "editAdditionalEmployeeAddress";

    }

    @RequestMapping(value = "/editAdditionalEmployeeAddress", method = RequestMethod.POST)
    public String postAdditionalEmployeeAddressEditForm(@ModelAttribute("address") Address address,
                                                          @ModelAttribute("person") Person person,
                                                          @ModelAttribute("employee") Employee employee) {

        addressRepository.save(address);

        return "redirect:/subject/additionalEmployeeAddresses?id=" + employee.getEmployee();

    }

    @RequestMapping(value = "/addAdditionalEmployeeAddress", method = RequestMethod.GET)
    public String getAdditionalEmployeeAddressAddForm(@ModelAttribute("person") Person person,
                                                      @ModelAttribute("employee") Employee employee, Model model) {

        Address address = new Address();
        model.addAttribute(address);

        return "addAdditionalEmployeeAddress";

    }

    @RequestMapping(value = "/addAdditionalEmployeeAddress", method = RequestMethod.POST)
    public String postAdditionalEmployeeAddressAddForm(@ModelAttribute("address") Address address, @ModelAttribute("employee") Employee employee) {

        address.setSubjectFk(employee.getPersonFk());
        address.setSubjectTypeFk(1L);
        address.setAddressTypeFk(2L);

        addressRepository.save(address);

        return "redirect:/subject/additionalEmployeeAddresses?id=" + employee.getEmployee();

    }


    @RequestMapping(value = "/changeToMainPersonAddress", method = RequestMethod.GET, params = "id")
    public String changeToMainPersonAddress(@RequestParam("id") long id, @ModelAttribute("person") Person person) {

        Address mainAddress = addressRepository.findBySubjectTypeFkAndSubjectFkAndAddressTypeFk(1L, person.getPerson(), 1L);
        mainAddress.setAddressTypeFk(2L);
        addressRepository.save(mainAddress);

        Address additionalAddress = addressRepository.findByAddress(id);
        additionalAddress.setAddressTypeFk(1L);
        addressRepository.save(additionalAddress);

        return "redirect:/subject/additionalPersonAddresses?id=" + person.getPerson();
    }

    @RequestMapping(value = "/changeToMainEnterpriseAddress", method = RequestMethod.GET, params = "id")
    public String changeToMainEnterpriseAddress(@RequestParam("id") long id, @ModelAttribute("enterprise") Enterprise enterprise) {

        Address mainAddress = addressRepository.findBySubjectTypeFkAndSubjectFkAndAddressTypeFk(2L, enterprise.getEnterprise(), 3L);
        mainAddress.setAddressTypeFk(2L);
        addressRepository.save(mainAddress);

        Address additionalAddress = addressRepository.findByAddress(id);
        additionalAddress.setAddressTypeFk(3L);
        addressRepository.save(additionalAddress);

        return "redirect:/subject/additionalEnterpriseAddresses?id=" + enterprise.getEnterprise();
    }

    @RequestMapping(value = "/changeToMainEmployeeAddress", method = RequestMethod.GET, params = "id")
    public String changeToMainEmployeeAddress(@RequestParam("id") long id, @ModelAttribute("employee") Employee employee) {

        Address mainAddress = addressRepository.findBySubjectTypeFkAndSubjectFkAndAddressTypeFk(1L, employee.getPersonFk(), 1L);
        mainAddress.setAddressTypeFk(2L);
        addressRepository.save(mainAddress);

        Address additionalAddress = addressRepository.findByAddress(id);
        additionalAddress.setAddressTypeFk(1L);
        addressRepository.save(additionalAddress);

        return "redirect:/subject/additionalEmployeeAddresses?id=" + employee.getEmployee();

    }
}
