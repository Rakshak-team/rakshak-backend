/**
 * ============================================================================
 * File: AuthResponse.java
 * Path: src/main/java/com/rakshak/dto/response/AuthResponse.java
 * ----------------------------------------------------------------------------
 * Description:
 * Data Transfer Object (DTO) returned after successful authentication.
 *
 * Responsibilities:
 * • Return JWT token
 * • Return authenticated user details
 * • Return account information
 * • Send authentication response to frontend
 * ============================================================================
 */

package com.rakshak.dto.response;

// Imports

import com.rakshak.enums.AccountStatus;
import com.rakshak.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ============================================================================
// Authentication Response DTO
// ============================================================================

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AuthResponse {

    // =========================================================================
    // Response Status
    // =========================================================================

    private boolean success;

    private String message;

    // =========================================================================
    // Authentication
    // =========================================================================

    private String token;

    @Builder.Default
    private String tokenType = "Bearer";

    // =========================================================================
    // User Information
    // =========================================================================

    private Long userId;

    private String firstName;

    private String lastName;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String bloodGroup;
    
    private Boolean enabled;

    // =========================================================================
    // User Role
    // =========================================================================

    private Role role;

    private AccountStatus accountStatus;

    private Boolean verified;

}