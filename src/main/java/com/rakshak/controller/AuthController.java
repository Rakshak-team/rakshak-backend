/**
 * ============================================================================
 * File: AuthController.java
 * Path: src/main/java/com/rakshak/controller/AuthController.java
 * ----------------------------------------------------------------------------
 * Description:
 * REST Controller for user authentication.
 *
 * Responsibilities:
 * • User Registration
 * • User Login
 * ============================================================================
 */

package com.rakshak.controller;

// Imports

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rakshak.dto.request.LoginRequest;
import com.rakshak.dto.request.RegisterRequest;
import com.rakshak.dto.response.AuthResponse;
import com.rakshak.service.UserService;

import jakarta.validation.Valid;

// ============================================================================
// Authentication Controller
// ============================================================================

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    // =========================================================================
    // Dependencies
    // =========================================================================

    private final UserService userService;

    // =========================================================================
    // Constructor
    // =========================================================================

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // =========================================================================
    // Register API
    // =========================================================================

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        AuthResponse response = userService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // =========================================================================
    // Login API
    // =========================================================================

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse response = userService.login(request);

        return ResponseEntity.ok(response);
    }
}