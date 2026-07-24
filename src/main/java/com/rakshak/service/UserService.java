/**
 * ============================================================================
 * File: UserService.java
 * Path: src/main/java/com/rakshak/service/UserService.java
 * ----------------------------------------------------------------------------
 * Description:
 * Service interface for user authentication and account management.
 *
 * Responsibilities:
 * • Register new users
 * • Authenticate existing users
 * • Fetch user details
 * • Manage user accounts
 * • Support future authentication features
 * ============================================================================
 */

package com.rakshak.service;

// Imports

import com.rakshak.dto.request.LoginRequest;
import com.rakshak.dto.request.RegisterRequest;
import com.rakshak.dto.response.AuthResponse;

// ============================================================================
// User Service Interface
// ============================================================================

public interface UserService {

    // =========================================================================
    // Authentication
    // =========================================================================

    /**
     * Registers a new user.
     *
     * @param request Registration request
     * @return Authentication response
     */
    AuthResponse register(RegisterRequest request);

    /**
     * Authenticates an existing user.
     *
     * @param request Login request
     * @return Authentication response
     */
    AuthResponse login(LoginRequest request);

}