/**
 * ============================================================================
 * File: ResourceNotFoundException.java
 * Path: src/main/java/com/rakshak/exception/ResourceNotFoundException.java
 * ----------------------------------------------------------------------------
 * Description:
 * Custom exception thrown when a requested resource is not found.
 *
 * Responsibilities:
 * • Handle resource not found scenarios
 * ============================================================================
 */

package com.rakshak.exception;

// ============================================================================
// Resource Not Found Exception
// ============================================================================

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // =========================================================================
    // Constructor
    // =========================================================================

    public ResourceNotFoundException(String message) {
        super(message);
    }

}