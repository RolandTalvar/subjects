package com.miinaroland.repository;

import com.miinaroland.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by rolandtalvar on 02/06/16.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByAddress(long address);

    Address findBySubjectTypeFkAndSubjectFkAndAddressTypeFk(long subjectTypeFk, long subjectFk, long addressTypeFk);
    List<Address> findBySubjectTypeFkAndAddressTypeFkAndSubjectFk(long subjectTypeFk, long addressTypeFk, long subjectFk);
}
