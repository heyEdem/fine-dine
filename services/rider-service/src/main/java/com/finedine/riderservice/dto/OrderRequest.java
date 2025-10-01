package com.finedine.riderservice.dto;


import lombok.Data;

@Data
public class OrderRequest {
    private Long id;
    private Long restaurantId;
    private Long customerId;
    private String status;
}
