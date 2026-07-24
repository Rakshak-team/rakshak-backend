/**
 * ============================================================================
 * File: SecurityUtils.java
 * Path: src/main/java/com/rakshak/util/SecurityUtils.java
 * ----------------------------------------------------------------------------
 * Description:
 * Utility class for accessing the currently authenticated user.
 *
 * Responsibilities:
 * • Get logged-in user's email
 * • Get authenticated principal
 * ============================================================================
 */

package com.rakshak.util;

// Imports

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.rakshak.security.CustomUserDetails;

// ============================================================================
// Security Utils
// ============================================================================

public final class SecurityUtils {

    // =========================================================================
    // Private Constructor
    // =========================================================================

    private SecurityUtils() {
    }

    // =========================================================================
    // Get Logged-in User Email
    // =========================================================================

    public static String getCurrentUserEmail() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return authentication.getName();
    }

    // =========================================================================
    // Get Logged-in User
    // =========================================================================

    public static CustomUserDetails getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return (CustomUserDetails) authentication.getPrincipal();
    }

}