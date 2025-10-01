package com.finedine.authservice.dto;

/**
 * DTO for resending OTP to a user's email.
 * Contains the email address and the type of OTP to be resent.
 */
public record ResendOtpRequest(
        String email,

        String type
) {
}
