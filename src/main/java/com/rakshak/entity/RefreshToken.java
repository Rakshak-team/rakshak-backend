package com.rakshak.entity;

/**
 * ============================================================================
 * File: RefreshToken.java
 * Path: src/main/java/com/rakshak/entity/RefreshToken.java
 * ----------------------------------------------------------------------------
 * Description:
 * Entity representing a refresh token issued to an authenticated user.
 *
 * Responsibilities:
 * • Store refresh tokens
 * • Associate refresh token with a user
 * • Track expiration
 * • Support secure logout and token rotation
 * ============================================================================
 */


import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ============================================================================
// Refresh Token Entity
// ============================================================================

@Entity
@Table(name = "refresh_tokens")

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RefreshToken {

    // =========================================================================
    // Primary Key
    // =========================================================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================================================================
    // Refresh Token
    // =========================================================================

    @Column(nullable = false, unique = true, length = 512)
    private String token;

    // =========================================================================
    // Associated User
    // =========================================================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    // =========================================================================
    // Token Expiration
    // =========================================================================

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    // =========================================================================
    // Audit
    // =========================================================================

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // =========================================================================
    // Automatically Set Creation Time
    // =========================================================================

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}