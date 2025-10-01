package com.finedine.restaurantservice.dto;


public record MenuItemRequest(
        String name,
        String description,
        Double price,
        String category,
        Boolean isAvailable
) {
}