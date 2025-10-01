package com.finedine.restaurantservice.controller;

import com.finedine.restaurantservice.dto.*;
import com.finedine.restaurantservice.security.SecurityUser;
import com.finedine.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/my-restaurant")
    @PreAuthorize("hasRole('RESTAURANT')")
    @ResponseStatus(HttpStatus.OK)
    public RestaurantResponse myRestaurant(@AuthenticationPrincipal SecurityUser securityUser) {
        return restaurantService.myRestaurant(securityUser);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Page<RestaurantResponse> allRestaurants(@PageableDefault Pageable pageable){
        return restaurantService.allRestaurants(pageable);
    }

    @GetMapping("/nearby")
    @PreAuthorize("hasRole('CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public Page<RestaurantResponse> availableRestaurants(
                                                    @PageableDefault Pageable pageable,
                                                    @RequestParam double userLat,
                                                    @RequestParam double userLon,
                                                    @RequestParam(defaultValue = "10") double radiusKm) {
        return restaurantService.nearByRestaurants(pageable, userLat, userLon, radiusKm);
    }

    @PatchMapping("/profile-update")
    @PreAuthorize("hasRole('RESTAURANT')")
    @ResponseStatus(HttpStatus.OK)
    public RestaurantResponse profileSettings(@AuthenticationPrincipal SecurityUser securityUser,
                                              @RequestBody RestaurantUpdateRequest request){
        return restaurantService.profileSettings(securityUser, request);
    }

    @PostMapping("/menu")
    @PreAuthorize("hasRole('RESTAURANT')")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericMessageResponse addMenuItem(@AuthenticationPrincipal SecurityUser securityUser,
                                              @RequestBody MenuItemRequest request) {
        return restaurantService.addMenuItem(securityUser, request);
    }

    @GetMapping ("/{restaurantId}/menu")
    @ResponseStatus(HttpStatus.OK)
    public Page<MenuItemResponse> getRestaurantMenu(@PathVariable Long restaurantId, @PageableDefault Pageable pageable){
        return restaurantService.getRestaurantMenu(restaurantId, pageable);
    }

    @PatchMapping("/menu/{menuItemId}")
    @PreAuthorize("hasRole('RESTAURANT')")
    @ResponseStatus(HttpStatus.OK)
    public GenericMessageResponse updateMenuItem(@AuthenticationPrincipal SecurityUser securityUser,
                                                 @PathVariable Long menuItemId,
                                                 @RequestBody MenuItemRequest request) {
        return restaurantService.updateMenuItem(securityUser, menuItemId, request);
    }

    @DeleteMapping("/menu/{menuItemId}")
    @PreAuthorize("hasRole('RESTAURANT')")
    @ResponseStatus(HttpStatus.OK)
    public GenericMessageResponse deleteMenuItem(@AuthenticationPrincipal SecurityUser securityUser,
                                                 @PathVariable Long menuItemId) {
        return restaurantService.deleteMenuItem(securityUser, menuItemId);
    }
}
