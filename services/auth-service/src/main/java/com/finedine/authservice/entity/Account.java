package com.finedine.authservice.entity;

import com.finedine.authservice.enums.AccountStatus;
import com.finedine.authservice.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an account entity in the system.
 * This class is used to store user account information including email, password, role, and status.
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "accounts")
public class Account {

    @Version
    private Long version = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String externalId;

    @Column(nullable = false, unique = true)
    private String email;

    String phoneNumber;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Instant lastEmailUpdate = null;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isEnabled;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isVerified;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDeleted;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return isEnabled() == account.isEnabled() && isVerified() == account.isVerified() && isDeleted() == account.isDeleted() &&
                Objects.equals(getId(), account.getId()) && Objects.equals(getEmail(), account.getEmail()) && Objects.equals(getPassword(),
                account.getPassword()) && getRole() == account.getRole() && Objects.equals(getCreatedAt(), account.getCreatedAt())
                && Objects.equals(getUpdatedAt(), account.getUpdatedAt()) && Objects.equals(getDeletedAt(), account.getDeletedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword(), getRole(), isEnabled(), isVerified(), isDeleted(),
                getCreatedAt(), getUpdatedAt(), getDeletedAt());
    }

}