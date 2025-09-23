package com.finedine.authservice.service;

import com.finedine.authservice.dto.ResendOtpRequest;
import com.finedine.authservice.dto.*;
import com.finedine.authservice.dto.signup.CustomerRegistrationRequest;
import com.finedine.authservice.dto.signup.RestaurantRegistrationRequest;
import com.finedine.authservice.dto.signup.RiderRegistrationRequest;

/**
 * Service interface for managing account-related operations.
 * Provides methods to retrieve account details, delete accounts, and fetch all verified users.
 */
public interface AuthService {

    /**
     * Handles the login process.
     * @param request The login request containing user credentials.
     * @return The login response containing the authentication token and other relevant information.
     */
    LoginResponse login (LoginRequest request);

    /**
     * Creates a new customer account based on the provided registration request.
     * @param request request The registration request containing customer details.
     */
    GenericMessageResponse registerCustomer(CustomerRegistrationRequest request);

    /**
     * Creates a new rider account based on the provided registration request.
     * @param request The registration request containing rider details.
     */
    GenericMessageResponse registerRider(RiderRegistrationRequest request);

    /**
     * Creates a new restaurant account based on the provided registration request.
     * @param request The registration request containing restaurant details.
     */
    GenericMessageResponse registerRestaurant(RestaurantRegistrationRequest request);

    SignUpSuccessResponse verifyAndSignUpUser(VerifyOtpDto otpDto);

    GenericMessageResponse resendOtp (ResendOtpRequest request);

    GenericMessageResponse resetPasswordRequest(PasswordResetRequest request);

    GenericMessageResponse resetPassword(PasswordReset request);



}
