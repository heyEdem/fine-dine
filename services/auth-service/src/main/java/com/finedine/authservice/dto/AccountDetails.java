package com.finedine.authservice.dto;

import com.finedine.authservice.enums.Role;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * Record representing the details of an account.
 * This record is used to encapsulate the account information
 * that is returned in various responses.
 */

@Builder
public record AccountDetails(
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    Role role,
    boolean enabled,
    boolean verified,
    LocalDateTime createdAt
) {
}
