package com.finedine.restaurantservice.dto;



public interface MenuItemResponse {
    String getName();
    String getDescription();
    Double getPrice();
    String getCategory();
    Boolean getAvailable();
}

