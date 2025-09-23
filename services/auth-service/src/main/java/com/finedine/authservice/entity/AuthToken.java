package com.finedine.authservice.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Entity representing authentication tokens including access and refresh tokens along with their expiration times.
 */

@Builder
@Getter
@Setter
public class AuthToken {
    private String accessToken;
    private String refreshToken;
    private final Instant accessTokenExpiresAt;
    private final Instant refreshTokenExpiresAt;
}
