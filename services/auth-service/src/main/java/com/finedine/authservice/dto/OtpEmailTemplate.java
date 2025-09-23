package com.finedine.authservice.dto;

import com.finedine.authservice.entity.OtpType;
import lombok.Builder;

/**
 * DTO for OTP email template specifying recipient, OTP code, and type of OTP.
 */
@Builder
public record OtpEmailTemplate(
        String to,
        String otp,
        OtpType otpType
) {
}
