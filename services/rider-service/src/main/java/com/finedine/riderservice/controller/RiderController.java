package com.finedine.riderservice.controller;

import com.finedine.riderservice.dto.GenericMessageResponse;
import com.finedine.riderservice.dto.OrderRequest;
import com.finedine.riderservice.entity.DeliveryStatus;
import com.finedine.riderservice.entity.Rider;
import com.finedine.riderservice.security.SecurityUser;
import com.finedine.riderservice.service.RiderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/riders")
public class RiderController {

    private final RiderService riderService;

    @GetMapping("/my-rider")
    @PreAuthorize("hasRole('RIDER')")
    @ResponseStatus(HttpStatus.OK)
    public Rider myProfile(@AuthenticationPrincipal SecurityUser securityUser) {
        return riderService.myProfile(securityUser);
    }

    @GetMapping("/online")
    @PreAuthorize("hasRole('RIDER')")
    @ResponseStatus(HttpStatus.OK)
    public GenericMessageResponse goOnline(@AuthenticationPrincipal SecurityUser securityUser) {
        return riderService.goOnline(securityUser);
    }

    @GetMapping("/offline")
    @PreAuthorize("hasRole('RIDER')")
    @ResponseStatus(HttpStatus.OK)
    public GenericMessageResponse goOffline(@AuthenticationPrincipal SecurityUser securityUser) {
        return riderService.goOffline(securityUser);
    }

    @GetMapping("/delivery-requests")
    @PreAuthorize("hasRole('RIDER')")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderRequest> getDeliveryRequests(@AuthenticationPrincipal SecurityUser securityUser) {
        return riderService.getAvailableDeliveryRequests(securityUser);
    }

    @PostMapping("/accept-delivery/{orderId}")
    @PreAuthorize("hasRole('RIDER')")
    @ResponseStatus(HttpStatus.OK)
    public GenericMessageResponse acceptDelivery(@PathVariable Long orderId, @AuthenticationPrincipal SecurityUser securityUser) {
        return riderService.acceptDelivery(orderId, securityUser);
    }

    @PostMapping("/delivery/{orderId}/status")
    @PreAuthorize("hasRole('RIDER')")
    @ResponseStatus(HttpStatus.OK)
    public GenericMessageResponse updateDeliveryStatus(@PathVariable Long orderId, @RequestBody DeliveryStatus status, @AuthenticationPrincipal SecurityUser securityUser) {
        return riderService.updateDeliveryStatus(orderId, status, securityUser);
    }
}
