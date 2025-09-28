package com.finedine.customerservice.dto;

import lombok.Builder;

@Builder
public record CustomerResponse(
        Long externalId,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address,
        String profilePictureUrl
) {
}
