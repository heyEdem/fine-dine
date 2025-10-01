package com.finedine.authservice.controller;

import com.finedine.authservice.dto.GenericMessageResponse;
import com.finedine.authservice.security.KeyManager;
import com.finedine.authservice.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.finedine.authservice.CustomMessages.KEYS_ROTATED_SUCCESSFULLY;

/**
 * Controller for managing JWT tokens and key rotation.
 * Provides endpoints to retrieve the JWK set and rotate keys.
 */

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final KeyManager keyManager;
    private final TokenManager tokenManager;

    @GetMapping("/.well-known/jwks.json")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getJwk() {

        return keyManager.getJWKSet().toPublicJWKSet().toJSONObject();
    }

    @PostMapping("/auth/keys/rotate")
    public GenericMessageResponse rotateKeys() {
        keyManager.rotateKeys();
        return new GenericMessageResponse(KEYS_ROTATED_SUCCESSFULLY);
    }

}
