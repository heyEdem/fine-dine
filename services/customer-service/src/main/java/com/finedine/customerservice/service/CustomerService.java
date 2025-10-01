package com.finedine.customerservice.service;

import com.finedine.customerservice.dto.CustomerCreationQueueObject;
import com.finedine.customerservice.entity.Customer;
import com.finedine.customerservice.security.SecurityUser;

public interface CustomerService {

    /**
     * Create a new customer based on the data received from the customer creation queue.
     *
     * @param data The data required to create a new customer.
     */
    void createCustomer(CustomerCreationQueueObject data);

    /**
     * Retrieve the profile of the currently authenticated customer.
     *
     * @param securityUser The security user representing the authenticated customer.
     * @return The customer's profile information.
     */
    Customer myCustomerProfile(SecurityUser securityUser);

}
