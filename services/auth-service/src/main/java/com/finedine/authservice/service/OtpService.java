package com.finedine.authservice.service;


import com.finedine.authservice.entity.OtpType;

public interface OtpService {

    void generateAndSendOtp(String email, OtpType type);

    boolean isValidOtp(String code, String email);

    void invalidateOtp(String code);
}
