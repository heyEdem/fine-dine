package com.finedine.customerservice.service;

import com.finedine.customerservice.dto.CustomerCreationQueueObject;
import com.finedine.customerservice.entity.Customer;
import com.finedine.customerservice.exception.NotFoundException;
import com.finedine.customerservice.exception.UnauthorizedException;
import com.finedine.customerservice.repository.CustomerRepository;
import com.finedine.customerservice.security.SecurityUser;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    /**
     {@inheritDoc}
     */
    @SqsListener(value = "fds-customer-registration-queue.fifo")
    @Override
    public void createCustomer(CustomerCreationQueueObject data) {
        List<String> addresses = new ArrayList<>();

        addresses.add(data.address());

        log.info("Received new customer data: {}", data);

        Customer customer = Customer.builder()
                .email(data.email())
                .externalId(data.externalId())
                .accountId(data.accountId())
                .firstName(data.firstName())
                .lastName(data.lastName())
                .phoneNumber(data.phoneNumber())
                .profilePictureUrl(data.profilePictureUrl())
                .address(addresses)
                .build();
        customerRepository.save(customer);
    }


    /**
     {@inheritDoc}
    */
    @Override
    public Customer myCustomerProfile(SecurityUser securityUser) {

        Customer customer = customerRepository.findByExternalId(securityUser.externalId())
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        if (securityUser.externalId() != null && !securityUser.externalId().equals(customer.getExternalId())) {
            throw new UnauthorizedException("Restricted action");
        }

        return customer;
    }

}
