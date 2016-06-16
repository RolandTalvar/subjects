package com.miinaroland.repository;

import com.miinaroland.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by miinaroland on 02/06/16.
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
