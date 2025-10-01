package com.finedine.authservice.async;

import lombok.Builder;
/**
 * DTO for customer registration queue.
 * This record is used to transfer customer registration data in asynchronous processing between auth and customer services.
 */
@Builder
public record CustomerCreationQueueObject(
        Long accountId,
        String email,
        String externalId,
        String address,
        String phoneNumber,
        String firstName,
        String lastName,
        String profilePictureUrl
) {}