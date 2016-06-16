package com.miinaroland.repository;

import com.miinaroland.model.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by miinaroland on 02/06/16.
 */
public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {
}
