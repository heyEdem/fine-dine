package com.finedine.authservice.repository;

import com.finedine.authservice.dto.VerifiedUsers;
import com.finedine.authservice.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Account entities.
 * Provides methods to perform CRUD operations on Account entities.
 */

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select a from Account a where a.email =:email and a.isVerified = true ")
    Optional<Account> findByEmailAndVerifiedAccount(String email);

    Optional<Account> findByEmail(String email);

    Optional<Account> findById(Long id);

    @Query("select a from Account a where a.isVerified = true ")
    Page<VerifiedUsers> findAllVerified(Pageable pageable);

    @Query("select a from Account a where a.isVerified = false ")
    List<Account> findAllUnverified();
}
