package com.finedine.authservice.dto;

import java.time.LocalDateTime;
/**
 * Interface representing a dto for retrieving all verified users.
 * This interface defines the structure of the data that will be returned when fetching
 * all verified users from the system.
 */

public interface VerifiedUsers {
    String getEmail();
    String getRole();
    String getPhoneNumber();
    boolean getIsEnabled();
    LocalDateTime getCreatedAt();
}
