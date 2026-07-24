/**
 * ============================================================================
 * File: InvalidCredentialsException.java
 * Path: src/main/java/com/rakshak/exception/InvalidCredentialsException.java
 * ----------------------------------------------------------------------------
 * Description:
 * Custom exception thrown when invalid login credentials are provided.
 *
 * Responsibilities:
 * • Handle invalid email or password during login
 * ============================================================================
 */

package com.rakshak.exception;

// ============================================================================
// Invalid Credentials Exception
// ============================================================================

public class InvalidCredentialsException extends RuntimeException {

    // =========================================================================
    // Constructor
    // =========================================================================
	private static final long serialVersionUID = 1L;
    public InvalidCredentialsException(String message) {
        super(message);
    }
}