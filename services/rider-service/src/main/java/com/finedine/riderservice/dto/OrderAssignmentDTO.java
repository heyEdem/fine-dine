package com.finedine.riderservice.dto;


public record OrderAssignmentDTO (
        Long orderId,
        Long riderId,
        String status
) {}