/**
 * ============================================================================
 * File: User.java
 * Path: src/main/java/com/rakshak/entity/User.java
 * ----------------------------------------------------------------------------
 * Description:
 * Represents a registered user of the Rakshak platform.
 *
 * Responsibilities:
 * • Store user profile information
 * • Support authentication and authorization
 * • Manage volunteer information
 * • Maintain medical volunteer details
 * • Persist user information in PostgreSQL
 * ============================================================================
 */

package com.rakshak.entity;

// Imports

import java.io.Serializable;

import com.rakshak.enums.AccountStatus;
import com.rakshak.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ============================================================================
// User Entity
// ============================================================================

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity

@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_user_email", columnList = "email"),
                @Index(name = "idx_user_phone", columnList = "phone_number")
        }
)

public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // =========================================================================
    // Basic Information
    // =========================================================================

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true, length = 15)
    private String phoneNumber;

    @Column(length = 5)
    private String bloodGroup;

    // =========================================================================
    // Authentication
    // =========================================================================

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    @Builder.Default
    private AccountStatus accountStatus = AccountStatus.ACTIVE;
    // =========================================================================
    // Medical Volunteer Details
    // =========================================================================

    @Column(length = 100)
    private String medicalProfession;

    @Column(length = 150)
    private String organization;

    @Column(length = 100)
    private String registrationNumber;

    // =========================================================================
    // Profile Information
    // =========================================================================

    @Column(length = 500)
    private String profileImageUrl;

    @Builder.Default
    @Column(nullable = false)
    private Boolean verified = false;
    
    @Builder.Default
    @Column(nullable = false)
    private Boolean enabled = true;

    // =========================================================================
    // Entity Lifecycle Methods
    // =========================================================================

    @PrePersist
    @PreUpdate
    private void normalizeFields() {

        if (email != null) {
            email = email.trim().toLowerCase();
        }

        if (firstName != null) {
            firstName = firstName.trim();
        }

        if (lastName != null) {
            lastName = lastName.trim();
        }

        if (phoneNumber != null) {
            phoneNumber = phoneNumber.trim();
        }

        if (bloodGroup != null) {
            bloodGroup = bloodGroup.trim().toUpperCase();
        }

        if (medicalProfession != null) {
            medicalProfession = medicalProfession.trim();
        }

        if (organization != null) {
            organization = organization.trim();
        }

        if (registrationNumber != null) {
            registrationNumber = registrationNumber.trim();
        }
    }

    // =========================================================================
    // Helper Methods
    // =========================================================================

    
    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public Boolean getEnabled() {
        return enabled;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isNormalVolunteer() {
        return role == Role.NORMAL_VOLUNTEER;
    }

    public boolean isMedicalVolunteer() {
        return role == Role.MEDICAL_VOLUNTEER;
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    public boolean isActive() {
        return accountStatus == AccountStatus.ACTIVE;
    }
}