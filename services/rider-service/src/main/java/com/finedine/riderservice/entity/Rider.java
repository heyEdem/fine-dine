package com.finedine.riderservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entity representing a rider in the system.
 * This class is used to store rider information including personal details, vehicle information, and current status.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "riders")

public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String externalId;

    @Column(nullable = false, updatable = false)
    private Long accountId;

    @Column(nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    private Availability availability;

    private String vehicleColor;

    private String licensePlateNumber;

    private Status status;

    @Column(nullable = false)
    private String profilePictureUrl;

    @Column
    private String currentLocation;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    private LocalDate deletedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rider)) return false;
        Rider rider = (Rider) o;
        return Objects.equals(getId(), rider.getId()) && Objects.equals(getEmail(), rider.getEmail()) &&
                Objects.equals(getFirstName(), rider.getFirstName()) && Objects.equals(getLastName(),
                rider.getLastName()) && Objects.equals(getPhoneNumber(), rider.getPhoneNumber()) &&
                Objects.equals(getVehicleType(), rider.getVehicleType()) && Objects.equals(getVehicleColor(),
                rider.getVehicleColor()) && Objects.equals(getLicensePlateNumber(), rider.getLicensePlateNumber())
                && getStatus() == rider.getStatus() && Objects.equals(getProfilePictureUrl(), rider.getProfilePictureUrl())
                && Objects.equals(getCreatedAt(), rider.getCreatedAt()) && Objects.equals(getUpdatedAt(),
                rider.getUpdatedAt()) && Objects.equals(getDeletedAt(), rider.getDeletedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getFirstName(), getLastName(), getPhoneNumber(), getVehicleType(),
                getVehicleColor(), getLicensePlateNumber(), getStatus(), getProfilePictureUrl(), getCreatedAt(),
                getUpdatedAt(), getDeletedAt());
    }
}
