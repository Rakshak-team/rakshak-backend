/**
 * ============================================================================
 * File: UserAlreadyExistsException.java
 * Path: src/main/java/com/rakshak/exception/UserAlreadyExistsException.java
 * ----------------------------------------------------------------------------
 * Description:
 * Custom exception thrown when a user tries to register with an email
 * or phone number that already exists.
 *
 * Responsibilities:
 * • Handle duplicate user registration
 * ============================================================================
 */

package com.rakshak.exception;

// ============================================================================
// User Already Exists Exception
// ============================================================================

public class UserAlreadyExistsException extends RuntimeException {

    // =========================================================================
    // Constructor
    // =========================================================================
	private static final long serialVersionUID = 1L;
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}