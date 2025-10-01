package com.finedine.authservice.security;

import com.finedine.authservice.entity.Account;
import com.finedine.authservice.util.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service to load user details by email for authentication.
 * Implements UserDetailsService to provide user details to Spring Security.
 */

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {

    private final Common common;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Account account = common.loadAccountByEmail(email);

        return new SecurityUser(account);
    }
}
