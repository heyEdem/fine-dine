package com.finedine.authservice.util;

import com.finedine.authservice.entity.Account;
import com.finedine.authservice.exception.NotFoundException;
import com.finedine.authservice.exception.UnverifiedAccountException;
import com.finedine.authservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Component;

import static com.finedine.authservice.CustomMessages.*;
import static com.finedine.authservice.CustomMessages.USER_NOT_FOUND;

@Slf4j
@Component
@RequiredArgsConstructor
public class Common {

    private final AccountRepository accountRepository;

    public Account loadAccount(String email, Long id) {
        if (email != null) {
            return accountRepository.findByEmail(email)
                    .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        } else if (id != null) {
            return accountRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        } else {
            throw new IllegalArgumentException("Either email or id must be provided");
        }
    }

    public Account loadAccountByEmail(String email) {
        return loadAccount(email, null);
    }

    public Account loadAccountById(Long id) {
        return loadAccount(null, id);
    }

    public void validateAccount(Account account){

        if (!account.isVerified()) {
            throw new UnverifiedAccountException(UNVERIFIED_ACCOUNT);
        }

        if(!account.isEnabled()) {
            throw new DisabledException(ACCOUNT_DISABLED);
        }

        log.info("User {} is verified and enabled", account.getEmail());
    }
}
