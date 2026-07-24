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

    // =========================================================================
    // Constructor Injection
    // =========================================================================

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
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

        // Generate JWT
        String jwtToken = jwtService.generateToken(user);

        // Build Response
        return AuthResponse.builder()
                .success(true)
                .message("Registration successful.")
                .token(jwtToken)
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

        // Generate JWT Token
        String jwtToken = jwtService.generateToken(user);

        // Build Response
        return AuthResponse.builder()
                .success(true)
                .message("Login successful.")
                .token(jwtToken)
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

}