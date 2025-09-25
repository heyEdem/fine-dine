package com.finedine.restaurantservice.repository;

import com.finedine.restaurantservice.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    boolean existsByRestaurantCode(String code);

//    Page<Restaurant> findAll(Pageable pageable);
    @Query("SELECT r FROM Restaurant r WHERE r.deletedAt IS NULL " +
            "ORDER BY " +
            "CASE WHEN r.openTime <= CURRENT_TIME AND r.closeTime > CURRENT_TIME THEN 0 ELSE 1 END, " +
            "r.restaurantName ASC")
    Page<Restaurant> findAll(Pageable pageable);

    @Query("SELECT r FROM Restaurant r WHERE r.externalId = :externalId AND r.deletedAt IS NULL")
     Optional<Restaurant> findByExternalId(@Param("externalId") String externalId);

    @Query("SELECT r FROM Restaurant r WHERE r.deletedAt IS NULL AND " +
            "(6371 * acos(cos(radians(:userLat)) * cos(radians(r.latitude)) * " +
            "cos(radians(r.longitude) - radians(:userLon)) + sin(radians(:userLat)) * " +
            "sin(radians(r.latitude)))) <= :radiusKm " +
            "ORDER BY " +
            "(6371 * acos(cos(radians(:userLat)) * cos(radians(r.latitude)) * " +
            "cos(radians(r.longitude) - radians(:userLon)) + sin(radians(:userLat)) * " +
            "sin(radians(r.latitude)))) ASC")
    Page<Restaurant> findAllByDeletedAtIsNullAndWithinRadius(@Param("userLat") double userLat,
                                                             @Param("userLon") double userLon,
                                                             @Param("radiusKm") double radiusKm,
                                                             Pageable pageable);
}