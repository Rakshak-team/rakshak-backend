/**
 * ============================================================================
 * File: RefreshTokenException.java
 * Path: src/main/java/com/rakshak/exception/RefreshTokenException.java
 * ----------------------------------------------------------------------------
 * Description:
 * Custom exception thrown when a refresh token is invalid, expired,
 * or cannot be processed.
 *
 * Responsibilities:
 * • Handle invalid refresh tokens
 * • Handle expired refresh tokens
 * • Provide meaningful authentication error messages
 * ============================================================================
 */

package com.rakshak.exception;

// ============================================================================
// Refresh Token Exception
// ============================================================================

public class RefreshTokenException extends RuntimeException {

    // =========================================================================
    // Constructor
    // =========================================================================

    /**
     * Creates a new RefreshTokenException with the specified message.
     *
     * @param message Exception message
     */
    public RefreshTokenException(String message) {
        super(message);
    }

}