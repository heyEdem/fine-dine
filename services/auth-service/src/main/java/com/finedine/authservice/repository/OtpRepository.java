package com.finedine.authservice.repository;

import com.finedine.authservice.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByCode(String code);
    Optional<Otp> findByEmail(String email);
    Optional<Otp> findFirstByEmailOrderByCreatedAtDesc(String email);
}
