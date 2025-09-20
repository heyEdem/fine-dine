package com.finedine.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/** Record representing a password reset request.
 * This record is used to encapsulate the data required for resetting a user's password,
 * including email, new password, confirmation of the new password, and the reset otp code.
 */
public record PasswordReset(
        @NotNull
        @NotBlank
        String email,

        @NotNull
        @NotBlank
        String password,

        @NotNull
        @NotBlank
        String confirmPassword,

        @NotNull
        @NotBlank
        String code
) {
}
