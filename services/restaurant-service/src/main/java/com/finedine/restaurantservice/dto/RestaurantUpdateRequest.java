package com.finedine.restaurantservice.dto;


import org.springframework.web.multipart.MultipartFile;


/**
 * DTO for restaurant profile settings update.
 * This record is used to change any restaurant profile data in API requests.
 */

public record RestaurantUpdateRequest(
    String restaurantName,
    String phoneNumber,
    String address,
    Double longitude,
    Double latitude,
    String cuisine,
    String description,
    MultipartFile logo
    ){
}
