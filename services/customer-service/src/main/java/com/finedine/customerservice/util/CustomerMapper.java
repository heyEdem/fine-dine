package com.finedine.customerservice.util;

import com.finedine.customerservice.dto.CustomerCreationQueueObject;
import com.finedine.customerservice.dto.CustomerResponse;
import com.finedine.customerservice.dto.CustomerUpdateRequest;
import com.finedine.customerservice.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponse toCustomerResponse(Customer customer);

    Customer toCustomer(CustomerCreationQueueObject data);
    void updateCustomerFromRequest(CustomerUpdateRequest request, @MappingTarget Customer restaurant);

}
