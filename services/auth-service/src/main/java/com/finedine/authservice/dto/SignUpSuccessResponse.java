package com.finedine.authservice.dto;

import lombok.Builder;
/**
 * Record representing a successful sign-up response.
 * This record contains the user's first name, last name, email, access token, and refresh token.
 */
@Builder
public record SignUpSuccessResponse(
        String firstname,
        String lastname,
        String email,
        String accessToken,
        String refreshToken
){
}
