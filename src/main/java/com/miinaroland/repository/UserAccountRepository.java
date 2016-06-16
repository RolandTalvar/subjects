package com.miinaroland.repository;

import com.miinaroland.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by miinaroland on 02/06/16.
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findOneByUsername(String username);
}
