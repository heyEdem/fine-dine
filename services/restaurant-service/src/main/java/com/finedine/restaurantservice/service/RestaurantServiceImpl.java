package com.finedine.restaurantservice.service;

import com.finedine.restaurantservice.dto.RestaurantQueueObject;
import com.finedine.restaurantservice.entity.Restaurant;
import com.finedine.restaurantservice.exception.NotFoundException;
import com.finedine.restaurantservice.exception.UnauthorizedException;
import com.finedine.restaurantservice.repository.RestaurantRepository;
import com.finedine.restaurantservice.security.SecurityUser;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.finedine.restaurantservice.util.CustomMessages.RESTAURANT_NOT_FOUND;
import static com.finedine.restaurantservice.util.CustomMessages.RESTRICTED_ACTION;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;
    private static final double AVERAGE_SPEED_KMH = 30.0;

    @SqsListener(value = "fds-restaurant-registration-queue.fifo")
    @Override
    public void createRestaurantEntry(RestaurantQueueObject data) {
        String restaurantCode;

        do {
            restaurantCode = generateRestaurantCode();
        } while (!isRestaurantCodeUnique(restaurantCode));

        log.info("Received new restaurant data: {}", data);

        Restaurant restaurant = Restaurant.builder()
                .email(data.email())
                .accountId(data.accountId())
                .externalId(data.externalId())
                .restaurantName(data.restaurantName())
                .address(data.address())
                .phone(data.phone())
                .cuisine(data.cuisine())
                .description(data.description())
                .logoUrl(data.imageUrl())
                .restaurantCode(restaurantCode)
                .build();
        restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant myRestaurant(SecurityUser securityUser) {

        log.info("Fetching restaurant for user: {}", securityUser);
        Restaurant restaurant = restaurantRepository.findByExternalId(securityUser.externalId())
                .orElseThrow(() -> new NotFoundException(RESTAURANT_NOT_FOUND));

        if (securityUser.externalId() != null && !securityUser.externalId().equals(restaurant.getExternalId())) {
            throw new UnauthorizedException(RESTRICTED_ACTION);
        }
        return restaurant;

    }



    private String generateRestaurantCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder(7);
        for (int i = 0; i < 7; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        return code.toString();
    }

    private boolean isRestaurantCodeUnique(String code) {
        return !restaurantRepository.existsByRestaurantCode(code);
    }
}
