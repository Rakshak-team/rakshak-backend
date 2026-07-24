/**
 * ============================================================================
 * File: LoginRequest.java
 * Path: src/main/java/com/rakshak/dto/request/LoginRequest.java
 * ----------------------------------------------------------------------------
 * Description:
 * Data Transfer Object (DTO) for user login requests.
 *
 * Responsibilities:
 * • Receive login credentials from frontend
 * • Validate login request
 * • Transfer validated data to the service layer
 * ============================================================================
 */

package com.rakshak.dto.request;

// Imports

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ============================================================================
// Login Request DTO
// ============================================================================

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LoginRequest {

    // =========================================================================
    // Authentication Credentials
    // =========================================================================

    @NotBlank(message = "Email is required.")
    @Email(message = "Please enter a valid email address.")
    @Size(max = 100, message = "Email cannot exceed 100 characters.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(
            min = 8,
            max = 100,
            message = "Password must be between 8 and 100 characters."
    )
    private String password;

    // =========================================================================
    // Utility Methods
    // =========================================================================

    @Override
    public String toString() {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                '}';
    }

}