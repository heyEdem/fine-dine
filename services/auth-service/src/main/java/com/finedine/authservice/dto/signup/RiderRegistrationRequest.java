package com.finedine.authservice.dto.signup;

import com.finedine.authservice.enums.VehicleType;
import com.finedine.authservice.util.contraints.ImageFiles;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import static com.finedine.authservice.CustomMessages.*;

/**
 * DTO for registering a new rider.
 * This record is used to transfer rider registration data in API requests.
 */

public record RiderRegistrationRequest(
    String email,
    String password,
    @NotBlank(message = NAME_NOT_BLANK)
    String firstName,
    @NotBlank(message = NAME_NOT_BLANK)
    String lastName,
    String phoneNumber,
    String address,
    VehicleType vehicleType,
    String licensePlateNumber,
    String vehicleColor,
    @ImageFiles(message = PROFILE_PHOTO_MUST_BE_VALID)
    MultipartFile image) {
}
