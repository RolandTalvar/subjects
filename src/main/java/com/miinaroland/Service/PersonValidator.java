package com.miinaroland.Service;

import com.miinaroland.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by rolandtalvar on 16/06/16.
 */
@Component
public class PersonValidator implements Validator {

    /**
     * This Validator validates *just* Person instances
     */
    public boolean supports(Class clazz) {
        return Person.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
//        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
        Person p = (Person) obj;
        String code = p.getIdentityCode();

        String[] codeArray = code.split("");

        long sum = 1 * Integer.parseInt(codeArray[0]) +
                2 * Integer.parseInt(codeArray[1]) +
                3 * Integer.parseInt(codeArray[2]) +
                4 * Integer.parseInt(codeArray[3]) +
                5 * Integer.parseInt(codeArray[4]) +
                6 * Integer.parseInt(codeArray[5]) +
                7 * Integer.parseInt(codeArray[6]) +
                8 * Integer.parseInt(codeArray[7]) +
                9 * Integer.parseInt(codeArray[8]) +
                10 * Integer.parseInt(codeArray[9]);

        long mod = sum % 11;

        if (mod != 10) {
            if (mod != Integer.parseInt(codeArray[10])) {
                e.rejectValue("identityCode", "identitycode.invalid");
            }
        }
    }
}