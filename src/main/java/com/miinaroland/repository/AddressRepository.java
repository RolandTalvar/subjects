package com.miinaroland.repository;

import com.miinaroland.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rolandtalvar on 02/06/16.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
