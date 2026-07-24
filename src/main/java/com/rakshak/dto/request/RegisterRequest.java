/**
 * ============================================================================
 * File: RegisterRequest.java
 * Path: src/main/java/com/rakshak/dto/request/RegisterRequest.java
 * ----------------------------------------------------------------------------
 * Description:
 * Data Transfer Object (DTO) for user registration requests.
 *
 * Responsibilities:
 * • Receive registration data from frontend
 * • Validate user input
 * • Support Normal & Medical Volunteer registration
 * • Transfer validated data to the service layer
 * ============================================================================
 */

package com.rakshak.dto.request;

// Imports

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ============================================================================
// Register Request DTO
// ============================================================================

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RegisterRequest {

    // =========================================================================
    // Basic Information
    // =========================================================================

    @NotBlank(message = "First name is required.")
    @Size(max = 50, message = "First name cannot exceed 50 characters.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(max = 50, message = "Last name cannot exceed 50 characters.")
    private String lastName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Please enter a valid email address.")
    @Size(max = 100, message = "Email cannot exceed 100 characters.")
    private String email;

    @NotBlank(message = "Phone number is required.")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Please enter a valid 10-digit Indian mobile number."
    )
    private String phoneNumber;

    @NotBlank(message = "Blood group is required.")
    @Pattern(
            regexp = "^(A|B|AB|O)[+-]$",
            message = "Invalid blood group."
    )
    private String bloodGroup;

    // =========================================================================
    // Authentication
    // =========================================================================

    @NotBlank(message = "Password is required.")
    @Size(
            min = 8,
            max = 100,
            message = "Password must be between 8 and 100 characters."
    )
    private String password;

    @NotBlank(message = "Confirm password is required.")
    @Size(
            min = 8,
            max = 100,
            message = "Confirm password must be between 8 and 100 characters."
    )
    private String confirmPassword;
    // =========================================================================
    // Volunteer Information
    // =========================================================================

    @NotBlank(message = "Volunteer type is required.")
    @Pattern(
            regexp = "^(NORMAL|MEDICAL)$",
            message = "Volunteer type must be NORMAL or MEDICAL."
    )
    private String volunteerType;

    // =========================================================================
    // Medical Volunteer Details
    // =========================================================================

    private String medicalProfession;

    private String organization;

    private String registrationNumber;

    // =========================================================================
    // Utility Methods
    // =========================================================================

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", volunteerType='" + volunteerType + '\'' +
                ", medicalProfession='" + medicalProfession + '\'' +
                ", organization='" + organization + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                '}';
    }

}