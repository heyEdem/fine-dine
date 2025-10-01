package com.finedine.authservice.dto;

/**
 * This class represents a request for user login, containing the user's email and password.
 * It is used to encapsulate the data required for authentication.
 */

public record LoginRequest (
        String email,
        String password
){
}
