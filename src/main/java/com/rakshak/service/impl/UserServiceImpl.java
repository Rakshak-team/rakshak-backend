/**
 * ============================================================================
 * File: UserServiceImpl.java
 * Path: src/main/java/com/rakshak/service/impl/UserServiceImpl.java
 * ----------------------------------------------------------------------------
 * Description:
 * Service implementation for user authentication and account management.
 *
 * Responsibilities:
 * • Register new users
 * • Authenticate existing users
 * • Encrypt passwords
 * • Generate JWT tokens
 * • Validate registration data
 * • Build authentication responses
 * ============================================================================
 */

package com.rakshak.service.impl;

// Imports

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rakshak.dto.request.LoginRequest;
import com.rakshak.dto.request.RegisterRequest;
import com.rakshak.dto.response.AuthResponse;
import com.rakshak.entity.User;
import com.rakshak.enums.AccountStatus;
import com.rakshak.enums.Role;
import com.rakshak.exception.UserAlreadyExistsException;
import com.rakshak.repository.UserRepository;
import com.rakshak.security.JwtService;
import com.rakshak.service.UserService;
import com.rakshak.exception.InvalidCredentialsException;
import com.rakshak.entity.RefreshToken;
import com.rakshak.service.RefreshTokenService;
import java.util.Optional;

import com.rakshak.dto.request.RefreshTokenRequest;
import com.rakshak.exception.RefreshTokenException;
// ============================================================================
// User Service Implementation
// ============================================================================

@Service
public class UserServiceImpl implements UserService {

    // =========================================================================
    // Dependencies
    // =========================================================================

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    
    private final RefreshTokenService refreshTokenService;

    // =========================================================================
    // Constructor Injection
    // =========================================================================

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            RefreshTokenService refreshTokenService
    ) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }
    // =========================================================================
    // Register User
    // =========================================================================

    @Override
    public AuthResponse register(RegisterRequest request) {

        // Validate password confirmation
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException(
                    "Password and Confirm Password do not match."
            );
        }

        // Check duplicate email
        if (userRepository.existsByEmail(request.getEmail())) {

            throw new UserAlreadyExistsException(
                    "Email is already registered."
            );

        }

        // Check duplicate phone number
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {

            throw new UserAlreadyExistsException(
                    "Phone number is already registered."
            );

        }

        // Create User Entity
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setBloodGroup(request.getBloodGroup());

        // Encrypt Password
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        // Assign Role
        if ("MEDICAL".equalsIgnoreCase(request.getVolunteerType())) {

            user.setRole(Role.MEDICAL_VOLUNTEER);
            user.setVerified(false);
            user.setEnabled(true);
            user.setAccountStatus(
                    AccountStatus.PENDING_VERIFICATION
            );

            user.setMedicalProfession(
                    request.getMedicalProfession()
            );

            user.setOrganization(
                    request.getOrganization()
            );

            user.setRegistrationNumber(
                    request.getRegistrationNumber()
            );

        } else {

            user.setRole(Role.NORMAL_VOLUNTEER);
            user.setVerified(true);
            user.setEnabled(true);
            user.setAccountStatus(AccountStatus.ACTIVE);
        }

        // Save User
        user = userRepository.save(user);


        // Build Response
        return AuthResponse.builder()
                .success(true)
                .message("Registration successful.")
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .bloodGroup(user.getBloodGroup())
                .enabled(user.getEnabled())
                .role(user.getRole())
                .accountStatus(user.getAccountStatus())
                .verified(user.getVerified())
                .build();
    }
    // =========================================================================
    // Login User
    // =========================================================================

    @Override
    public AuthResponse login(LoginRequest request) {

        // Find user by email
    	User user = userRepository.findByEmail(request.getEmail())
    			.orElseThrow(() ->
    	        new InvalidCredentialsException(
    	                "Invalid email or password."
    	        )
    	);

        // Verify password
    	if (!passwordEncoder.matches(
    	        request.getPassword(),
    	        user.getPassword()
    	)) {

    	    throw new InvalidCredentialsException(
    	            "Invalid email or password."
    	    );
    	}

    	// Generate Access Token
    	String accessToken = jwtService.generateAccessToken(user);

    	// Generate & Store Refresh Token
    	RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        // Build Response
        return AuthResponse.builder()
                .success(true)
                .message("Login successful.")
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .bloodGroup(user.getBloodGroup())
                .enabled(user.getEnabled())
                .role(user.getRole())
                .accountStatus(user.getAccountStatus())
                .verified(user.getVerified())
                .build();
    }
 // =========================================================================
 // Refresh Access Token
 // =========================================================================

 @Override
 public AuthResponse refreshToken(RefreshTokenRequest request) {

     RefreshToken refreshToken = refreshTokenService
             .findByToken(request.getRefreshToken())
             .orElseThrow(() ->
                     new RefreshTokenException("Invalid refresh token.")
             );

     refreshTokenService.verifyExpiration(refreshToken);

     User user = refreshToken.getUser();

     String accessToken = jwtService.generateAccessToken(user);

     return AuthResponse.builder()
             .success(true)
             .message("Access token refreshed successfully.")
             .accessToken(accessToken)
             .refreshToken(refreshToken.getToken())
             .userId(user.getId())
             .firstName(user.getFirstName())
             .lastName(user.getLastName())
             .fullName(user.getFullName())
             .email(user.getEmail())
             .phoneNumber(user.getPhoneNumber())
             .bloodGroup(user.getBloodGroup())
             .enabled(user.getEnabled())
             .role(user.getRole())
             .accountStatus(user.getAccountStatus())
             .verified(user.getVerified())
             .build();

 }

 // =========================================================================
 // Logout User
 // =========================================================================

 @Override
 public void logout(String refreshToken) {

     Optional<RefreshToken> token = refreshTokenService.findByToken(refreshToken);

     if (token.isPresent()) {
         refreshTokenService.deleteByUser(token.get().getUser());
     }

 }

}