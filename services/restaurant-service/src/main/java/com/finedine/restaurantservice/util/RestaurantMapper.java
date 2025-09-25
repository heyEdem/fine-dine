package com.finedine.restaurantservice.util;

import com.finedine.restaurantservice.dto.RestaurantQueueObject;
import com.finedine.restaurantservice.dto.RestaurantUpdateRequest;
import com.finedine.restaurantservice.dto.RestaurantResponse;
import com.finedine.restaurantservice.entity.Restaurant;
import org.mapstruct.*;

import java.time.LocalTime;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RestaurantMapper {
    @Mapping(target = "isOpen", expression = "java(isRestaurantOpen(restaurant.getOpenTime(), restaurant.getCloseTime()))")
    RestaurantResponse toRestaurantResponse(Restaurant restaurant);

    Restaurant toRestaurant(RestaurantQueueObject data);

    void updateRestaurantFromRequest(RestaurantUpdateRequest request, @MappingTarget Restaurant restaurant);

    default boolean isRestaurantOpen(LocalTime openTime, LocalTime closeTime) {
        if (openTime == null || closeTime == null) {
            return false;
        }
        LocalTime now = LocalTime.now();
        if (closeTime.isBefore(openTime)) {
            return !now.isBefore(openTime) || !now.isAfter(closeTime);
        }
        return !now.isBefore(openTime) && !now.isAfter(closeTime);
    }
}
