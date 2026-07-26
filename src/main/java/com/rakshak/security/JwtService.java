/**
 * ============================================================================
 * File: JwtService.java
 * Path: src/main/java/com/rakshak/security/JwtService.java
 * ----------------------------------------------------------------------------
 * Description:
 * Service responsible for generating, validating, and extracting information
 * from JSON Web Tokens (JWT) used for authentication in the Rakshak backend.
 *
 * Responsibilities:
 * • Generate JWT access tokens
 * • Validate incoming JWTs
 * • Extract claims from JWTs
 * • Extract authenticated user's email
 * • Check token expiration
 * ============================================================================
 */

package com.rakshak.security;

// Imports

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rakshak.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

// ============================================================================
// JWT Service
// ============================================================================

@Service
public class JwtService {

    // =========================================================================
    // Access Token Configuration
    // =========================================================================

    @Value("${jwt.access.secret}")
    private String accessSecret;

    @Value("${jwt.access.expiration}")
    private Long accessExpiration;

    // =========================================================================
    // Access Token Signing Key
    // =========================================================================

    /**
     * Creates the cryptographic signing key using the configured JWT secret.
     *
     * @return Secret signing key
     */
    private SecretKey getAccessSigningKey() {

        return Keys.hmacShaKeyFor(
                accessSecret.getBytes(StandardCharsets.UTF_8)
        );

    }

    // =========================================================================
    // Generate JWT Access Token
    // =========================================================================

    /**
     * Generates a JWT access token for the authenticated user.
     *
     * @param user Authenticated user
     * @return JWT access token
     */
    public String generateAccessToken(User user) {

        Map<String, Object> claims = new HashMap<>();

        // Store additional information inside the token
        claims.put("role", user.getRole().name());
        claims.put("verified", user.getVerified());

        return generateAccessToken(claims, user);

    }

    /**
     * Generates a JWT access token with custom claims.
     *
     * @param extraClaims Additional claims
     * @param user Authenticated user
     * @return JWT access token
     */
    private String generateAccessToken(
            Map<String, Object> extraClaims,
            User user
    ) {

        Date issuedAt = new Date();

        Date expirationDate = new Date(
                issuedAt.getTime() + accessExpiration
        );

        return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getEmail())
                .issuedAt(issuedAt)
                .expiration(expirationDate)
                .signWith(getAccessSigningKey())
                .compact();

    }

    // =========================================================================
    // Extract Claims
    // =========================================================================

    /**
     * Extracts the user's email (subject) from the JWT token.
     *
     * @param token JWT token
     * @return User email
     */
    public String extractEmail(String token) {

        return extractClaim(
                token,
                Claims::getSubject
        );

    }

    /**
     * Extracts a specific claim from the JWT token.
     *
     * @param token JWT token
     * @param claimsResolver Function used to extract a specific claim
     * @param <T> Claim type
     * @return Extracted claim
     */
    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver
    ) {

        Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);

    }

    /**
     * Extracts all claims from the JWT token.
     *
     * @param token JWT token
     * @return JWT claims
     */
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getAccessSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    // =========================================================================
    // Token Expiration
    // =========================================================================

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param token JWT token
     * @return Expiration date
     */
    public Date extractExpiration(String token) {

        return extractClaim(
                token,
                Claims::getExpiration
        );

    }

    /**
     * Checks whether the JWT token has expired.
     *
     * @param token JWT token
     * @return true if expired, otherwise false
     */
    public boolean isTokenExpired(String token) {

        return extractExpiration(token)
                .before(new Date());

    }

    // =========================================================================
    // Token Validation
    // =========================================================================

    /**
     * Validates the JWT token against the given user.
     *
     * @param token JWT token
     * @param user Authenticated user
     * @return true if token is valid, otherwise false
     */
    public boolean isTokenValid(
            String token,
            User user
    ) {

        String email = extractEmail(token);

        return email.equals(user.getEmail())
                && !isTokenExpired(token);

    }

}