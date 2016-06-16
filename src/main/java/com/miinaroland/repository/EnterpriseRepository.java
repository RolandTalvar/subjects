package com.miinaroland.repository;

import com.miinaroland.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by miinaroland on 02/06/16.
 */
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

    Enterprise findByEnterprise(long enterprise);
}
