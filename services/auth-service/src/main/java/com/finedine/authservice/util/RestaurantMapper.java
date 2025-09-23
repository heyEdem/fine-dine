package com.finedine.authservice.util;

import com.finedine.authservice.async.RestaurantQueueObject;
import com.finedine.authservice.dto.signup.RestaurantRegistrationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantQueueObject toQueueObject(RestaurantRegistrationRequest request);
}
