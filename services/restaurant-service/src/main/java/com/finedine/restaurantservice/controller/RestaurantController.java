package com.finedine.restaurantservice.controller;

import com.finedine.restaurantservice.entity.Restaurant;
import com.finedine.restaurantservice.security.SecurityUser;
import com.finedine.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/my-restaurant")
    @PreAuthorize("hasRole('RESTAURANT')")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant myRestaurant(@AuthenticationPrincipal SecurityUser securityUser) {
        log.info("Fetching restaurant for user: {}", securityUser);
        return restaurantService.myRestaurant(securityUser);
    }

}
