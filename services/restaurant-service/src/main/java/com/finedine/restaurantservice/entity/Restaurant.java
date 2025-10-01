package com.finedine.restaurantservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @Column(updatable = false)
    private Long accountId;

    @Column(nullable = false, unique = true, updatable = false)
    private String externalId;

    @Column(nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false, unique = true, updatable = false)
    private String restaurantCode;

    @Column(nullable = false)
    private String phone;

    private String logoUrl;

    private String address;

    private Double latitude;

    private Double longitude;

    private String cuisine;

    private String description;

    @Column
    private LocalTime openTime;

    @Column
    private LocalTime closeTime;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant that)) return false;
        return Objects.equals(getRestaurantId(), that.getRestaurantId()) && Objects.equals(getAccountId(), that.getAccountId()) && Objects.equals(getExternalId(), that.getExternalId()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getRestaurantName(), that.getRestaurantName()) && Objects.equals(getRestaurantCode(), that.getRestaurantCode()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getLogoUrl(), that.getLogoUrl()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getLatitude(), that.getLatitude()) && Objects.equals(getLongitude(), that.getLongitude()) && Objects.equals(getCuisine(), that.getCuisine()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getOpenTime(), that.getOpenTime()) && Objects.equals(getCloseTime(), that.getCloseTime()) && Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getUpdatedAt(), that.getUpdatedAt()) && Objects.equals(getDeletedAt(), that.getDeletedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRestaurantId(), getAccountId(), getExternalId(), getEmail(), getRestaurantName(), getRestaurantCode(), getPhone(), getLogoUrl(), getAddress(), getLatitude(), getLongitude(), getCuisine(), getDescription(), getOpenTime(), getCloseTime(), getCreatedAt(), getUpdatedAt(), getDeletedAt());
    }
}
