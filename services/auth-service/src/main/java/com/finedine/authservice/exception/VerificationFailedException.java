package com.finedine.authservice.exception;

public class VerificationFailedException extends RuntimeException{
    public VerificationFailedException(String message) {
        super(message);
    }
}