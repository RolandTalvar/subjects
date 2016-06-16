package com.miinaroland.repository;

import com.miinaroland.model.EnterprisePersonRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by miinaroland on 02/06/16.
 */
public interface EnterprisePersonRelationRepository extends JpaRepository<EnterprisePersonRelation, Long> {

    List<EnterprisePersonRelation> findByEnterpriseFk(long enterpriseFk);
}
