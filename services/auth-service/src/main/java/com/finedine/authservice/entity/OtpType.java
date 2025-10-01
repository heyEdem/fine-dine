package com.finedine.authservice.entity;

/**
 * This enum represents the different types of One-Time Password (OTP) that can be used in the application.
 * The available OTP types are:
 * - CREATE: Used for creating a new account or resending an otp.
 * - RESET: Used for resetting an existing account password.
 */
public enum OtpType {
    CREATE,
    RESET
}
