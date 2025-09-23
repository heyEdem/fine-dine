package com.finedine.restaurantservice.repository;

import com.finedine.restaurantservice.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    boolean existsByRestaurantCode(String code);

    @Query("SELECT r FROM Restaurant r WHERE r.externalId = :externalId AND r.deletedAt IS NULL")
     Optional<Restaurant> findByExternalId(@Param("externalId") String externalId);
}