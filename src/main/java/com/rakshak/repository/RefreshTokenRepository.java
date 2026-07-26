/**
 * ============================================================================
 * File: RefreshTokenRepository.java
 * Path: src/main/java/com/rakshak/repository/RefreshTokenRepository.java
 * ----------------------------------------------------------------------------
 * Description:
 * Repository for performing database operations on Refresh Tokens.
 *
 * Responsibilities:
 * • Find refresh token by token value
 * • Delete refresh tokens for a user
 * • Support authentication and logout workflows
 * ============================================================================
 */

package com.rakshak.repository;

// Imports

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rakshak.entity.RefreshToken;
import com.rakshak.entity.User;

// ============================================================================
// Refresh Token Repository
// ============================================================================

@Repository
public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, Long> {

    // =========================================================================
    // Find Refresh Token
    // =========================================================================

    Optional<RefreshToken> findByToken(String token);

    // =========================================================================
    // Delete All Refresh Tokens Of A User
    // =========================================================================

    void deleteByUser(User user);

}