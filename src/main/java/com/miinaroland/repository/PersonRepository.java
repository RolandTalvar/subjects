package com.miinaroland.repository;

import com.miinaroland.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by miinaroland on 02/06/16.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByPerson(long person);
}
