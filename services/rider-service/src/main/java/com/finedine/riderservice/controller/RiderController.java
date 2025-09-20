package com.finedine.riderservice.controller;

import com.finedine.riderservice.entity.Rider;
import com.finedine.riderservice.security.SecurityUser;
import com.finedine.riderservice.service.RiderService;
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
@RequestMapping("/api/v1/riders")
public class RiderController {

    private final RiderService riderService;

    @GetMapping("/my-rider")
    @PreAuthorize("hasRole('RIDER')")
    @ResponseStatus(HttpStatus.OK)
    public Rider myRestaurant(@AuthenticationPrincipal SecurityUser securityUser) {
        return riderService.myRider(securityUser);
    }

}
