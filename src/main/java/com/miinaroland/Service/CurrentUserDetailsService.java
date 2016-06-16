package com.miinaroland.Service;

import com.miinaroland.model.CurrentUser;
import com.miinaroland.model.UserAccount;
import com.miinaroland.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by miinaroland on 16/06/16.
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService {



    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CurrentUser user = new CurrentUser();

        UserAccount userAccount = userAccountRepository.findOneByUsername(username);

        if (userAccount == null) {
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        }

        user.setUsername(userAccount.getUsername());
        user.setPassword(userAccount.getPassw());

        return user;
    }
}