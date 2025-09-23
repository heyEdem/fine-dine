package com.finedine.riderservice.service;

import com.finedine.riderservice.dto.RiderRegistrationQueue;
import com.finedine.riderservice.entity.Rider;
import com.finedine.riderservice.entity.Status;
import com.finedine.riderservice.exception.NotFoundException;
import com.finedine.riderservice.exception.UnauthorizedException;
import com.finedine.riderservice.repository.RiderRepository;
import com.finedine.riderservice.security.SecurityUser;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final RiderRepository riderRepository;

    @SqsListener(value = "fds-rider-registration-queue.fifo")
    @Override
    public void createRider(RiderRegistrationQueue data) {

        log.info("Received new rider data: {}", data);

        Rider rider = Rider.builder()
                .email(data.email())
                .accountId(data.accountId())
                .externalId(data.externalId())
                .firstName(data.firstName())
                .licensePlateNumber(data.licensePlateNumber() != null ? data.licensePlateNumber() : "N/A")
                .lastName(data.lastName())
                .phoneNumber(data.phoneNumber())
                .vehicleColor(data.vehicleColor())
                .vehicleType(data.vehicleType())
                .address(data.address())
                .profilePictureUrl(data.profilePictureUrl())
                .status(Status.ACTIVE)
                .build();
        riderRepository.save(rider);
    }
    @Override
    public Rider myRider(SecurityUser securityUser) {

        Rider restaurant = riderRepository.findByExternalId(securityUser.externalId())
                .orElseThrow(() -> new NotFoundException("Rider not found"));

        if (securityUser.externalId() != null && !securityUser.externalId().equals(restaurant.getExternalId())) {
            throw new UnauthorizedException("Restricted Action");
        }
        return restaurant;

    }
}
