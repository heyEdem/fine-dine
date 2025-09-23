package com.finedine.authservice.service;

import com.finedine.authservice.dto.*;
import com.finedine.authservice.entity.Account;
import com.finedine.authservice.security.SecurityUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    /**
     * Retrieves account details.
     *
     * @param id The ID of the account to retrieve.
     * @return The account details.
     */
    Account getAccount (Long id);

    /**
     * Retrieves a paginated list of all verified users.
     *
     * @param pageable Pagination information.
     * @return A page containing a list of verified users.
     */
    Page<VerifiedUsers> getAllVerifiedUsers(Pageable pageable);

    /**
     * Deletes an account by its ID.
     *
     * @param id The ID of the account to delete.
     * @return A generic message response indicating the success or failure of the operation.
     */
    GenericMessageResponse deleteAccount(Long id);

    /**
     *  Finds an account by its email address.
     *
     * @param email The email address of the account to find.
     * @return The account associated with the given email, or null if not found.
     */
    Account findAccountByEmail(String email);

    /**
     * Retrieves the account details of the currently authenticated user.
     *
     * @return The account details of the current user.
     */
    AccountDetails myAccount(SecurityUser securityUser);
}
