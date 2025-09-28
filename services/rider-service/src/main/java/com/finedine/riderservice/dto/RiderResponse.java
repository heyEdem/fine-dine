package com.finedine.riderservice.dto;

public record RiderResponse(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String vehicleNumber,
        String vehicleType,
        String status,
        String profilePictureUrl
) {
}
