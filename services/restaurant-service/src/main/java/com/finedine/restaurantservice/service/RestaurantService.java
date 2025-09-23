package com.finedine.restaurantservice.service;

import com.finedine.restaurantservice.dto.RestaurantQueueObject;
import com.finedine.restaurantservice.entity.Restaurant;
import com.finedine.restaurantservice.security.SecurityUser;

public interface RestaurantService {
    void createRestaurantEntry(RestaurantQueueObject data);

    Restaurant myRestaurant(SecurityUser securityUser);

}
