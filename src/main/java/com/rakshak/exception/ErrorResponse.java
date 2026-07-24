/**
 * ============================================================================
 * File: ErrorResponse.java
 * Path: src/main/java/com/rakshak/exception/ErrorResponse.java
 * ----------------------------------------------------------------------------
 * Description:
 * Standard error response returned by the application whenever an exception
 * occurs.
 *
 * Responsibilities:
 * • Store error status
 * • Store error message
 * • Store timestamp
 * ============================================================================
 */

package com.rakshak.exception;

// Imports

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ============================================================================
// Error Response DTO
// ============================================================================

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    // =========================================================================
    // Fields
    // =========================================================================

    private boolean success;

    private int status;

    private String message;

    private LocalDateTime timestamp;
}