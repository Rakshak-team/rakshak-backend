/**
 * ============================================================================
 * File: Role.java
 * Path: src/main/java/com/rakshak/enums/Role.java
 * ----------------------------------------------------------------------------
 * Description:
 * Represents all user roles available in the Rakshak platform.
 *
 * Responsibilities:
 * • Define available user roles
 * • Support authentication and authorization
 * • Differentiate volunteer categories
 * • Support future administrative functionalities
 * ============================================================================
 */

package com.rakshak.enums;

// Imports

// ============================================================================
// User Roles
// ============================================================================

public enum Role {

    // =========================================================================
    // Default User Role
    // =========================================================================

    USER("User"),

    // =========================================================================
    // Volunteer Roles
    // =========================================================================

    NORMAL_VOLUNTEER("Normal Volunteer"),

    MEDICAL_VOLUNTEER("Medical Volunteer"),

    // =========================================================================
    // Administrative Role
    // =========================================================================

    ADMIN("Administrator");

    // Display name of the role
    private final String displayName;

    // Constructor to initialize display name
    Role(String displayName) {
        this.displayName = displayName;
    }

    // Returns the display name of the role
    public String getDisplayName() {
        return displayName;
    }

}