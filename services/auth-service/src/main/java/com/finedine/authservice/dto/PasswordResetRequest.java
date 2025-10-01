package com.finedine.authservice.dto;

/**
 * Record representing a password reset request.
 * This record contains the email address of the user requesting a password reset.
 */
public record PasswordResetRequest(String email) {
}
