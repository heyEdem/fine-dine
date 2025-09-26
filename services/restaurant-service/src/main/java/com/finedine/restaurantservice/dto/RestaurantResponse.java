package com.finedine.restaurantservice.dto;


import lombok.Builder;

@Builder
public record RestaurantResponse(
        Long restaurantId,
        String externalId,
        String email,
        String restaurantName,
        String restaurantCode,
        String phone,
        String logoUrl,
        String address,
        Double latitude,
        Double longitude,
        String cuisine,
        String description,
        Integer travelTimeMinutes,
        boolean isOpen
) {}
