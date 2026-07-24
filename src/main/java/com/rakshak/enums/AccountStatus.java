/**
 * ============================================================================
 * File: AccountStatus.java
 * Path: src/main/java/com/rakshak/enums/AccountStatus.java
 * ----------------------------------------------------------------------------
 * Description:
 * Represents all possible account states in the Rakshak platform.
 *
 * Responsibilities:
 * • Track user account status
 * • Restrict blocked or suspended users
 * • Support account verification flow
 * • Enable future admin account management
 * ============================================================================
 */

package com.rakshak.enums;

// Imports

// ============================================================================
// Account Status
// ============================================================================

public enum AccountStatus {

    // =========================================================================
    // Active Account
    // =========================================================================

    ACTIVE("Active"),

    // =========================================================================
    // Verification Pending
    // =========================================================================

    PENDING_VERIFICATION("Pending Verification"),

    // =========================================================================
    // Suspended Account
    // =========================================================================

    SUSPENDED("Suspended"),

    // =========================================================================
    // Blocked Account
    // =========================================================================

    BLOCKED("Blocked");

    // Display name shown in responses and dashboard
    private final String displayName;

    // Constructor
    AccountStatus(String displayName) {
        this.displayName = displayName;
    }

    // Returns readable account status
    public String getDisplayName() {
        return displayName;
    }

}