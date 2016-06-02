package com.miinaroland.repository;

import com.miinaroland.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rolandtalvar on 02/06/16.
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
