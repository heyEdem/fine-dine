package com.finedine.riderservice.service;


import com.finedine.riderservice.dto.GenericMessageResponse;
import com.finedine.riderservice.dto.OrderRequest;
import com.finedine.riderservice.dto.RiderRegistrationQueue;
import com.finedine.riderservice.entity.DeliveryStatus;
import com.finedine.riderservice.entity.Rider;
import com.finedine.riderservice.security.SecurityUser;

import java.util.List;

public interface RiderService {
    /**
     * Create a new rider registration request.
     *
     * @param data the rider registration data
     */
    void createRider(RiderRegistrationQueue data);

    /**
     * Get the rider profile of the currently authenticated rider.
     *
     * @param securityUser the security user containing authentication details
     * @return the rider profile
     */
    Rider myProfile(SecurityUser securityUser);

    /**
     * Set the rider's status to online.
     *
     * @param securityUser the security user containing authentication details
     * @return a generic message response indicating success
     */
    GenericMessageResponse goOnline(SecurityUser securityUser);

    /**
     * Set the rider's status to offline.
     *
     * @param securityUser the security user containing authentication details
     * @return a generic message response indicating success
     */
    GenericMessageResponse goOffline(SecurityUser securityUser);

    /**
     * Get a list of available delivery requests for the rider.
     *
     * @param securityUser the security user containing authentication details
     * @return a list of available delivery requests
     */
    List<OrderRequest> getAvailableDeliveryRequests(SecurityUser securityUser);

    /**
     * Accept a delivery request.
     *
     * @param orderId the ID of the order to accept
     * @param securityUser the security user containing authentication details
     * @return a generic message response indicating success
     */
    GenericMessageResponse acceptDelivery(Long orderId, SecurityUser securityUser);

    /**
     * Update the rider's current location.
     *
     * @param securityUser the security user containing authentication details
     * @param location the new location of the rider
     * @return the updated location as a string
     */
    String updateLocation(SecurityUser securityUser, String location);

    /**
     * Update the status of a delivery.
     *
     * @param orderId the ID of the order to update
     * @param status the new status of the delivery
     * @param securityUser the security user containing authentication details
     * @return a generic message response indicating success
     */
    GenericMessageResponse updateDeliveryStatus(Long orderId, DeliveryStatus status, SecurityUser securityUser);
}
