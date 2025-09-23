package com.finedine.authservice.async;

import com.finedine.authservice.enums.VehicleType;
import lombok.Builder;
/**
 * DTO for rider registration queue.
 * This record is used to transfer rider registration data in asynchronous processing between auth and rider services.
 */
@Builder
public record RiderRegistrationQueue (
        Long accountId,
        String email,
        String externalId,
        String address,
        VehicleType vehicleType,
        String licensePlateNumber,
        String vehicleColor,
        String phoneNumber,
        String firstName,
        String lastName,
        String profilePictureUrl
) {
}
