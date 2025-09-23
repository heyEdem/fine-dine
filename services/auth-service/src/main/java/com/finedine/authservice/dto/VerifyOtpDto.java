package com.finedine.authservice.dto;

/**
 * DTO for verifying OTP code sent to a user's email.
 */
public record VerifyOtpDto(
        String code,
        String email
        ){
}
