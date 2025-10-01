package com.finedine.authservice.async;

import lombok.Builder;

import java.time.LocalTime;

/**
 * DTO for restaurant registration queue.
 * This record is used to transfer restaurant registration data in asynchronous processing between auth and restaurant services.
 */
@Builder
public record RestaurantQueueObject(
        Long accountId,
        String email,
        String externalId,
        String address,
        String phone,
        String cuisine,
        String description,
        String restaurantName,
        String imageUrl,
        Double longitude,
        Double latitude,
        LocalTime openTime,
        LocalTime closeTime
) {}