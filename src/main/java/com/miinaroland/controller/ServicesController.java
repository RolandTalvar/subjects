package com.miinaroland.controller;

import com.google.gson.Gson;
import com.miinaroland.dao.EnterpriseSearchDAO;
import com.miinaroland.model.*;
import com.miinaroland.dao.PersonSearchDAO;
import com.miinaroland.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/subject/search/")
public class ServicesController {

    @Autowired
    PersonSearchDAO personSearchDAO;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    EnterpriseSearchDAO enterpriseSearchDAO;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EnterpriseRepository enterpriseRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    StructUnitRepository structUnitRepository;

    @Autowired
    EnterprisePersonRelationRepository enterprisePersonRelationRepository;

    private static final Logger logger = LoggerFactory.getLogger("subjectLogger");

    @RequestMapping(value = "structureUnits", method = RequestMethod.GET)
    public String getStructureUnits(@RequestParam(value = "enterpriseID") Long enterpriseID) {

        logger.info("Getting structure units...");

        List<StructUnit> structUnitList = structUnitRepository.findByEnterpriseFk(enterpriseID);
        Gson gson = new Gson();
        return gson.toJson(structUnitList);
    }

    @RequestMapping(value = "removeEnterprisePersonRelation/{id:\\d+}", method = RequestMethod.GET)
    public @ResponseBody void removeEnterprisePersonRelation(@PathVariable("id") long id) {

        logger.info("Deleting Enterprise Person Relation...");

        enterprisePersonRelationRepository.delete(id);

    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String getPersonList(@RequestParam(value = "firstName", required = false) String firstName,
                                   @RequestParam(value = "lastName", required = false) String lastName,
                                   @RequestParam(value = "identityCode", required = false) String identityCode,
                                   @RequestParam(value = "birthDate", required = false) java.sql.Date birthDate,
//                                    Address parameters
                                   @RequestParam(value = "country", required = false) String country,
                                   @RequestParam(value = "county", required = false) String county,
                                   @RequestParam(value = "townVillage", required = false) String townVillage,
                                   @RequestParam(value = "streetAddress", required = false) String streetAddress,
                                   @RequestParam(value = "zipcode", required = false) String zipcode,
//                                   Contact parameters
                                   @RequestParam(value = "contactValueText", required = false) String contactValueText,
//                                   Attributes parameters
                                   @RequestParam(value = "attributeValueText", required = false) String attributeValueText,
                                   @RequestParam(value = "attributeValueNumber", required = false) Long attributeValueNumber,
                                   @RequestParam(value = "attributeValueDate", required = false) java.sql.Date attributeValueDate
    ) {

        logger.info("Doing person search");


        List<Long> personIDsList = personSearchDAO.getPersonIdsBySearch(firstName, lastName, identityCode, birthDate,
                country, county, townVillage, streetAddress, zipcode, contactValueText, attributeValueText, attributeValueNumber, attributeValueDate, 1);

        List<SubjectSearchResult> personList = new ArrayList<>();

        for (Long personID : personIDsList) {
            Person person = personRepository.findByPerson(personID);
            if (person != null) {
                SubjectSearchResult subjectSearchResult = new SubjectSearchResult();
                subjectSearchResult.setId(person.getPerson());
                subjectSearchResult.setSubjectType(1);

                subjectSearchResult.setFirstName(person.getFirstName());
                subjectSearchResult.setLastName(person.getLastName());
                subjectSearchResult.setIdentityCode(person.getIdentityCode());
                subjectSearchResult.setBirthDate(person.getBirthDate());

                Address address = addressRepository.findBySubjectTypeFkAndSubjectFkAndAddressTypeFk(1L, personID, 1L);
                subjectSearchResult.setCountry(address.getCountry());
                subjectSearchResult.setCounty(address.getCounty());
                subjectSearchResult.setTownVillage(address.getTownVillage());
                subjectSearchResult.setStreetAddress(address.getStreetAddress());
                subjectSearchResult.setZipcode(address.getZipcode());

                personList.add(subjectSearchResult);
            }

        }


        Gson gson = new Gson();
        return gson.toJson(personList);
    }

//    @RequestMapping(value = "/personIDs", method = RequestMethod.GET)
//    public String getPersonIDsListJSON(@RequestParam(value = "firstName", required = false) String firstName,
//                                   @RequestParam(value = "lastName", required = false) String lastName,
//                                   @RequestParam(value = "identityCode", required = false) String identityCode,
//                                   @RequestParam(value = "birthDate", required = false) java.sql.Date birthDate,
////                                    Address parameters
//                                   @RequestParam(value = "country", required = false) String country,
//                                   @RequestParam(value = "county", required = false) String county,
//                                   @RequestParam(value = "townVillage", required = false) String townVillage,
//                                   @RequestParam(value = "streetAddress", required = false) String streetAddress,
//                                   @RequestParam(value = "zipcode", required = false) String zipcode,
////                                   Contact parameters
//                                   @RequestParam(value = "contactValueText", required = false) String contactValueText,
////                                   Attributes parameters
//                                   @RequestParam(value = "attributeValueText", required = false) String attributeValueText,
//                                   @RequestParam(value = "attributeValueNumber", required = false) Long attributeValueNumber,
//                                   @RequestParam(value = "attributeValueDate", required = false) java.sql.Date attributeValueDate
//                                   ) {
//
//        logger.info("Doing person IDs search");
//
//        List<Long> personIDsList = personSearchDAO.getPersonIdsBySearch(firstName, lastName, identityCode, birthDate,
//                country, county, townVillage, streetAddress, zipcode, contactValueText, attributeValueText, attributeValueNumber, attributeValueDate);
//
//        Gson gson = new Gson();
//        return gson.toJson(personIDsList);
//    }

    @RequestMapping(value = "/enterprise", method = RequestMethod.GET)
    public String getEnterpriseList(@RequestParam(value = "name", required = false) String name,
//                                    Address parameters
                                    @RequestParam(value = "country", required = false) String country,
                                    @RequestParam(value = "county", required = false) String county,
                                    @RequestParam(value = "townVillage", required = false) String townVillage,
                                    @RequestParam(value = "streetAddress", required = false) String streetAddress,
                                    @RequestParam(value = "zipcode", required = false) String zipcode,
//                                   Contact parameters
                                    @RequestParam(value = "contactValueText", required = false) String contactValueText,
//                                   Attributes parameters
                                    @RequestParam(value = "attributeValueText", required = false) String attributeValueText,
                                    @RequestParam(value = "attributeValueNumber", required = false) Long attributeValueNumber,
                                    @RequestParam(value = "attributeValueDate", required = false) java.sql.Date attributeValueDate
    ) {

        logger.info("Doing enterprise search");

        List<Long> enterpriseIDsList = enterpriseSearchDAO.getEnterpriseIdsBySearch(name, country, county, townVillage,
                streetAddress, zipcode, contactValueText, attributeValueText, attributeValueNumber, attributeValueDate, 2);

        List<SubjectSearchResult> enterpriseList = new ArrayList<>();

        for (Long enterpriseID : enterpriseIDsList) {
            Enterprise enterprise = enterpriseRepository.findByEnterprise(enterpriseID);
            if (enterprise != null) {
                SubjectSearchResult subjectSearchResult = new SubjectSearchResult();
                subjectSearchResult.setId(enterprise.getEnterprise());
                subjectSearchResult.setSubjectType(2);

                subjectSearchResult.setLastName(enterprise.getName());

                Address address = addressRepository.findBySubjectTypeFkAndSubjectFkAndAddressTypeFk(2L, enterpriseID, 3L);
                subjectSearchResult.setCountry(address.getCountry());
                subjectSearchResult.setCounty(address.getCounty());
                subjectSearchResult.setTownVillage(address.getTownVillage());
                subjectSearchResult.setStreetAddress(address.getStreetAddress());
                subjectSearchResult.setZipcode(address.getZipcode());

                enterpriseList.add(subjectSearchResult);
            }

        }

        Gson gson = new Gson();
        return gson.toJson(enterpriseList);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getEmployeeList(@RequestParam(value = "firstName", required = false) String firstName,
                                  @RequestParam(value = "lastName", required = false) String lastName,
                                  @RequestParam(value = "identityCode", required = false) String identityCode,
                                  @RequestParam(value = "birthDate", required = false) java.sql.Date birthDate,
//                                    Address parameters
                                  @RequestParam(value = "country", required = false) String country,
                                  @RequestParam(value = "county", required = false) String county,
                                  @RequestParam(value = "townVillage", required = false) String townVillage,
                                  @RequestParam(value = "streetAddress", required = false) String streetAddress,
                                  @RequestParam(value = "zipcode", required = false) String zipcode,
//                                   Contact parameters
                                  @RequestParam(value = "contactValueText", required = false) String contactValueText,
//                                   Attributes parameters
                                  @RequestParam(value = "attributeValueText", required = false) String attributeValueText,
                                  @RequestParam(value = "attributeValueNumber", required = false) Long attributeValueNumber,
                                  @RequestParam(value = "attributeValueDate", required = false) java.sql.Date attributeValueDate
    ) {

        logger.info("Doing employee search");

        List<Long> personIDsList = personSearchDAO.getPersonIdsBySearch(firstName, lastName, identityCode, birthDate,
                country, county, townVillage, streetAddress, zipcode, contactValueText, attributeValueText, attributeValueNumber, attributeValueDate, 3);

        List<SubjectSearchResult> employeeList = new ArrayList<>();

        for (Long personID : personIDsList) {
            Employee employee = employeeRepository.findByPersonFk(personID);
            if (employee != null) {
                SubjectSearchResult subjectSearchResult = new SubjectSearchResult();
                subjectSearchResult.setId(employee.getEmployee());
                subjectSearchResult.setSubjectType(3);

                Person person = personRepository.findByPerson(personID);
                subjectSearchResult.setFirstName(person.getFirstName());
                subjectSearchResult.setLastName(person.getLastName());
                subjectSearchResult.setIdentityCode(person.getIdentityCode());
                subjectSearchResult.setBirthDate(person.getBirthDate());

                Address address = addressRepository.findBySubjectTypeFkAndSubjectFkAndAddressTypeFk(1L, personID, 1L);
                subjectSearchResult.setCountry(address.getCountry());
                subjectSearchResult.setCounty(address.getCounty());
                subjectSearchResult.setTownVillage(address.getTownVillage());
                subjectSearchResult.setStreetAddress(address.getStreetAddress());
                subjectSearchResult.setZipcode(address.getZipcode());

                employeeList.add(subjectSearchResult);
            }

        }

        Gson gson = new Gson();
        return gson.toJson(employeeList);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String getCustomerList(@RequestParam(value = "firstName", required = false) String firstName,
                                  @RequestParam(value = "lastName", required = false) String lastName,
                                  @RequestParam(value = "identityCode", required = false) String identityCode,
                                  @RequestParam(value = "birthDate", required = false) java.sql.Date birthDate,
//                                    Address parameters
                                  @RequestParam(value = "country", required = false) String country,
                                  @RequestParam(value = "county", required = false) String county,
                                  @RequestParam(value = "townVillage", required = false) String townVillage,
                                  @RequestParam(value = "streetAddress", required = false) String streetAddress,
                                  @RequestParam(value = "zipcode", required = false) String zipcode,
//                                   Contact parameters
                                  @RequestParam(value = "contactValueText", required = false) String contactValueText,
//                                   Attributes parameters
                                  @RequestParam(value = "attributeValueText", required = false) String attributeValueText,
                                  @RequestParam(value = "attributeValueNumber", required = false) Long attributeValueNumber,
                                  @RequestParam(value = "attributeValueDate", required = false) java.sql.Date attributeValueDate) {

        logger.info("Doing customer search");

        List<Long> personIDsList = personSearchDAO.getPersonIdsBySearch(firstName, lastName, identityCode, birthDate,
                country, county, townVillage, streetAddress, zipcode, contactValueText, attributeValueText, attributeValueNumber, attributeValueDate, 4);
        List<Long> enterpriseIDsList = enterpriseSearchDAO.getEnterpriseIdsBySearch(lastName, country, county, townVillage, streetAddress, zipcode, contactValueText, attributeValueText, attributeValueNumber, attributeValueDate, 4);

        List<SubjectSearchResult> customerList = new ArrayList<>();

        for (Long personID : personIDsList) {
            Customer customer = customerRepository.findBySubjectFkAndSubjectTypeFk(personID, 1L);
            if (customer != null) {
                SubjectSearchResult subjectSearchResult = new SubjectSearchResult();
                subjectSearchResult.setId(personID);
                subjectSearchResult.setSubjectType(1);

                Person person = personRepository.findByPerson(personID);
                subjectSearchResult.setFirstName(person.getFirstName());
                subjectSearchResult.setLastName(person.getLastName());
                subjectSearchResult.setIdentityCode(person.getIdentityCode());
                subjectSearchResult.setBirthDate(person.getBirthDate());


                Address address = addressRepository.findBySubjectTypeFkAndSubjectFkAndAddressTypeFk(1L, personID, 1L);
                subjectSearchResult.setCountry(address.getCountry());
                subjectSearchResult.setCounty(address.getCounty());
                subjectSearchResult.setTownVillage(address.getTownVillage());
                subjectSearchResult.setStreetAddress(address.getStreetAddress());
                subjectSearchResult.setZipcode(address.getZipcode());

                customerList.add(subjectSearchResult);
            }

        }

        for (Long enterpriseID : enterpriseIDsList) {
            Customer customer = customerRepository.findBySubjectFkAndSubjectTypeFk(enterpriseID, 2L);
            if (customer != null) {
                SubjectSearchResult subjectSearchResult = new SubjectSearchResult();
                subjectSearchResult.setId(enterpriseID);
                subjectSearchResult.setSubjectType(2);

                Enterprise enterprise = enterpriseRepository.findByEnterprise(enterpriseID);
                subjectSearchResult.setLastName(enterprise.getName());

                Address address = addressRepository.findBySubjectTypeFkAndSubjectFkAndAddressTypeFk(2L, enterpriseID, 3L);
                subjectSearchResult.setCountry(address.getCountry());
                subjectSearchResult.setCounty(address.getCounty());
                subjectSearchResult.setTownVillage(address.getTownVillage());
                subjectSearchResult.setStreetAddress(address.getStreetAddress());
                subjectSearchResult.setZipcode(address.getZipcode());

                customerList.add(subjectSearchResult);
            }

        }

        Gson gson = new Gson();
        return gson.toJson(customerList);
    }

}
