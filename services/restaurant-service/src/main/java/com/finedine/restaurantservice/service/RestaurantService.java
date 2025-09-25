package com.finedine.restaurantservice.service;

import com.finedine.restaurantservice.dto.*;
import com.finedine.restaurantservice.security.SecurityUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestaurantService {
    void createRestaurantEntry(RestaurantQueueObject data);

    RestaurantResponse myRestaurant(SecurityUser securityUser);

    Page<RestaurantResponse> allRestaurants(Pageable pageable);

    Page<RestaurantResponse> nearByRestaurants( Pageable pageable, double userLat, double userLon, double radiusKm);

    RestaurantResponse profileSettings(SecurityUser securityUser, RestaurantUpdateRequest request);

    GenericMessageResponse addMenuItem(SecurityUser securityUser, MenuItemRequest request);

    GenericMessageResponse updateMenuItem(SecurityUser securityUser, Long menuItemId, MenuItemRequest request);

    GenericMessageResponse deleteMenuItem(SecurityUser securityUser, Long menuItemId);

    Page<MenuItemResponse> getRestaurantMenu(Long restaurantId, Pageable pageable);

}
