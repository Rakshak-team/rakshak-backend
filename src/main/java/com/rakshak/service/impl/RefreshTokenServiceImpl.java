/**
 * ============================================================================
 * File: RefreshTokenServiceImpl.java
 * Path: src/main/java/com/rakshak/service/impl/RefreshTokenServiceImpl.java
 * ----------------------------------------------------------------------------
 * Description:
 * Service implementation responsible for creating, validating and deleting
 * refresh tokens.
 *
 * Responsibilities:
 * • Generate refresh tokens
 * • Store refresh tokens
 * • Validate refresh token expiration
 * • Delete refresh tokens
 * ============================================================================
 */

package com.rakshak.service.impl;

// Imports

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rakshak.entity.RefreshToken;
import com.rakshak.entity.User;
import com.rakshak.repository.RefreshTokenRepository;
import com.rakshak.service.RefreshTokenService;

// ============================================================================
// Refresh Token Service Implementation
// ============================================================================

@Service
public class RefreshTokenServiceImpl
        implements RefreshTokenService {

    // =========================================================================
    // Dependencies
    // =========================================================================

    private final RefreshTokenRepository refreshTokenRepository;

    // =========================================================================
    // Refresh Token Expiration
    // =========================================================================

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenDuration;

    // =========================================================================
    // Constructor
    // =========================================================================

    public RefreshTokenServiceImpl(
            RefreshTokenRepository refreshTokenRepository
    ) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    // =========================================================================
    // Create Refresh Token
    // =========================================================================

    @Override
    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = RefreshToken.builder()

                .user(user)

                .token(UUID.randomUUID().toString())

                .expiryDate(
                        LocalDateTime.now()
                                .plusSeconds(refreshTokenDuration / 1000)
                )

                .build();

        return refreshTokenRepository.save(refreshToken);

    }

    // =========================================================================
    // Find Refresh Token
    // =========================================================================

    @Override
    public Optional<RefreshToken> findByToken(String token) {

        return refreshTokenRepository.findByToken(token);

    }

    // =========================================================================
    // Verify Expiration
    // =========================================================================

    @Override
    public RefreshToken verifyExpiration(
            RefreshToken refreshToken
    ) {

        if (refreshToken.getExpiryDate()
                .isBefore(LocalDateTime.now())) {

            refreshTokenRepository.delete(refreshToken);

            throw new RuntimeException(
                    "Refresh token has expired. Please login again."
            );
        }

        return refreshToken;

    }

    // =========================================================================
    // Delete User Refresh Tokens
    // =========================================================================

    @Override
    public void deleteByUser(User user) {

        refreshTokenRepository.deleteByUser(user);

    }

}