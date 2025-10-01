package com.finedine.authservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;

/** Record representing a login response.
 * This record encapsulates the data that is returned
 * after a successful login attempt.
 */
@Builder
public record LoginResponse (
        String email,
        String role,
        String accessToken,
        String refreshToken,
        boolean isEnabled,
        boolean isVerified,
        LocalDateTime createdAt
){
}
