package com.miinaroland.repository;

import com.miinaroland.model.StructUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by rolandtalvar on 02/06/16.
 */
public interface StructUnitRepository extends JpaRepository<StructUnit, Long> {

    List<StructUnit> findByEnterpriseFk(long enterpriseFk);
}
