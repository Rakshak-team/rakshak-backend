/**
 * ============================================================================
 * File: RefreshTokenService.java
 * Path: src/main/java/com/rakshak/service/RefreshTokenService.java
 * ----------------------------------------------------------------------------
 * Description:
 * Service interface for managing refresh tokens.
 *
 * Responsibilities:
 * • Generate refresh tokens
 * • Validate refresh tokens
 * • Delete refresh tokens
 * • Retrieve refresh tokens
 * ============================================================================
 */

package com.rakshak.service;

// Imports

import java.util.Optional;

import com.rakshak.entity.RefreshToken;
import com.rakshak.entity.User;

// ============================================================================
// Refresh Token Service
// ============================================================================

public interface RefreshTokenService {

    // =========================================================================
    // Create Refresh Token
    // =========================================================================

    RefreshToken createRefreshToken(User user);

    // =========================================================================
    // Find Refresh Token
    // =========================================================================

    Optional<RefreshToken> findByToken(String token);

    // =========================================================================
    // Validate Refresh Token
    // =========================================================================

    RefreshToken verifyExpiration(RefreshToken refreshToken);

    // =========================================================================
    // Delete User Refresh Tokens
    // =========================================================================

    void deleteByUser(User user);

}