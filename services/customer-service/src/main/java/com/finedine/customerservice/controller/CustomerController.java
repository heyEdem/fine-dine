package com.finedine.customerservice.controller;

import com.finedine.customerservice.entity.Customer;
import com.finedine.customerservice.security.SecurityUser;
import com.finedine.customerservice.service.CustomerService;
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
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer-profile")
    @PreAuthorize("hasRole('CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public Customer myRestaurant(@AuthenticationPrincipal SecurityUser securityUser) {
        return customerService.myCustomerProfile(securityUser);
    }

}
