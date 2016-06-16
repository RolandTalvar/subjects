package com.miinaroland.controller;

import com.miinaroland.model.*;
import com.miinaroland.repository.EntPerRelationTypeRepository;
import com.miinaroland.repository.EnterprisePersonRelationRepository;
import com.miinaroland.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by miinaroland on 02/06/16.
 */
@Controller
@RequestMapping("/subject")
@SessionAttributes({"enterprise", "enterprisePersonRelation"})
public class NewEnterpriseRelatedPersonController {

    private static final Logger logger = LoggerFactory.getLogger("subjectLogger");


    @Autowired
    PersonRepository personRepository;

    @Autowired
    EntPerRelationTypeRepository entPerRelationTypeRepository;

    @Autowired
    EnterprisePersonRelationRepository enterprisePersonRelationRepository;


    @RequestMapping(value = "/addNewEnterpriseRelatedPerson", method = RequestMethod.GET)
    public String getAddNewEnterpriseRelatedPerson(@ModelAttribute("enterprise") Enterprise enterprise, Model model) {

        logger.info("Gettings new enterprise related person form");

        PersonListWrapper personListWrapper = new PersonListWrapper();
        List<Person> personList = personRepository.findAll();
        personListWrapper.setPersonList(personList);
        model.addAttribute(personListWrapper);

        EntPerRelationTypeListWrapper entPerRelationTypeListWrapper = new EntPerRelationTypeListWrapper();
        List<EntPerRelationType> entPerRelationTypeList = entPerRelationTypeRepository.findAll();
        entPerRelationTypeListWrapper.setEntPerRelationTypeList(entPerRelationTypeList);
        model.addAttribute(entPerRelationTypeListWrapper);

        EnterprisePersonRelation enterprisePersonRelation = new EnterprisePersonRelation();
        enterprisePersonRelation.setEnterpriseFk(enterprise.getEnterprise());
        model.addAttribute(enterprisePersonRelation);

        return "addNewEnterpriseRelatedPerson";
    }

    @RequestMapping(value = "/addNewEnterpriseRelatedPerson", method = RequestMethod.POST)
    public String postAddNewEnterpriseRelatedPerson(@ModelAttribute("enterprise") Enterprise enterprise,
                                                    @ModelAttribute("enterprisePersonRelation") EnterprisePersonRelation enterprisePersonRelation) {

        logger.info("Posting new enterprise related person form");

        enterprisePersonRelationRepository.save(enterprisePersonRelation);

        return "redirect:/subject/editEnterprise?id=" + enterprise.getEnterprise();
    }


}
