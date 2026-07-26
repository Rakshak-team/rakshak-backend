/**
 * ============================================================================
 * File: GlobalExceptionHandler.java
 * Path: src/main/java/com/rakshak/exception/GlobalExceptionHandler.java
 * ----------------------------------------------------------------------------
 * Description:
 * Global exception handler for the entire application.
 *
 * Responsibilities:
 * • Handle custom exceptions
 * • Handle validation errors
 * • Return standard error responses
 * ============================================================================
 */

package com.rakshak.exception;

// Imports

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// ============================================================================
// Global Exception Handler
// ============================================================================

@RestControllerAdvice
public class GlobalExceptionHandler {

    // =========================================================================
    // User Already Exists
    // =========================================================================

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(
            UserAlreadyExistsException exception) {

        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .status(HttpStatus.CONFLICT.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    // =========================================================================
    // Invalid Credentials
    // =========================================================================

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
            InvalidCredentialsException exception) {

        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    // =========================================================================
    // Refresh Token Exception
    // =========================================================================

    @ExceptionHandler(RefreshTokenException.class)
    public ResponseEntity<ErrorResponse> handleRefreshTokenException(
            RefreshTokenException exception) {

        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    // =========================================================================
    // Resource Not Found
    // =========================================================================

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException exception) {

        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .status(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    // =========================================================================
    // Validation Errors
    // =========================================================================

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception) {

        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .status(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    // =========================================================================
    // Illegal Argument Exception
    // =========================================================================

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException exception) {

        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    // =========================================================================
    // Generic Exception
    // =========================================================================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception exception) {

        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

}