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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

// ============================================================================
// Authentication Controller
// ============================================================================

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(
        name = "Authentication APIs",
        description = "APIs for user registration and login."
)
public class AuthController {

    // =========================================================================
    // Dependencies
    // =========================================================================

    private final UserService userService;

    // =========================================================================
    // Register API
    // =========================================================================

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
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

    @Operation(summary = "Authenticate user and generate JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse response = userService.login(request);

        return ResponseEntity.ok(response);
    }

}