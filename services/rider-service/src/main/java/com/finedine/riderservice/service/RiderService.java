package com.finedine.riderservice.service;


import com.finedine.riderservice.dto.RiderRegistrationQueue;
import com.finedine.riderservice.entity.Rider;
import com.finedine.riderservice.security.SecurityUser;

public interface RiderService {
    void createRider(RiderRegistrationQueue data);

    Rider myRider(SecurityUser securityUser);

}
