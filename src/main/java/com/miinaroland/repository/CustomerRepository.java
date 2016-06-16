package com.miinaroland.repository;

import com.miinaroland.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by miinaroland on 02/06/16.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findBySubjectFkAndSubjectTypeFk(long subjectFk, long subjectTypeFk);
}
