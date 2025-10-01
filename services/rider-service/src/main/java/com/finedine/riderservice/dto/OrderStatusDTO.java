package com.finedine.riderservice.dto;


import com.finedine.riderservice.entity.DeliveryStatus;
import lombok.Builder;

@Builder
public record OrderStatusDTO (
        Long orderId,
        DeliveryStatus status
) {}
